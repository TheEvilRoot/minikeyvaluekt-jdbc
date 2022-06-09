package com.theevilroot.mkv.jdbc

import java.net.URI
import java.sql.Connection
import java.sql.Driver
import java.sql.DriverPropertyInfo
import java.util.*
import java.util.logging.Logger

class MkvDriver : Driver {

    companion object {
        const val DRIVER_NAME = "MkvDriver"
        const val DRIVER_VERSION_MAJOR = 1
        const val DRIVER_VERSION_MINOR = 0
        const val DRIVER_VERSION_MKV = "1.3"
        const val DRIVER_VERSION = "$DRIVER_VERSION_MAJOR.$DRIVER_VERSION_MINOR-$DRIVER_VERSION_MKV"
        const val DRIVER_URL = "jdbc:mkv:http://localhost:3000"
        const val DRIVER_CLASS = "com.theevilroot.mkv.jdbc.MkvDriver"
        const val DRIVER_VENDOR = "TheEvilRoot"
        const val DRIVER_DESCRIPTION = "JDBC driver for MiniKeyValue"

        const val PROP_PRE_CONNECT_TO_MASTER = "mkv.preconnect"
    }

    /**
     * Return Master Server HTTP URL from JDBC connection uri
     * Throw IllegalArgumentException if JDBC URI cannot be used to acquire Master Server URI
     *
     * @param jdbcUri JDBC connection uri
     * @throws IllegalArgumentException if argument uri is not valid MkvDriver iro
     * @return Master Server HTTP URL
     */
    private fun validateUri(jdbcUri: String?): String {
        if (jdbcUri == null) {
            throw IllegalArgumentException("JDBC uri cannot be null")
        }
        val uri = URI(jdbcUri)
        if (uri.scheme != "jdbc") {
            throw IllegalArgumentException("JDBC uri scheme must be 'jdbc' not '${uri.scheme}'")
        }
        val mkvUri = URI(uri.schemeSpecificPart)
        if (mkvUri.scheme != "mkv") {
            throw IllegalArgumentException("JDBC Mkv URI scheme must be 'mkv' not '${mkvUri.scheme}'")
        }
        val httpUri = URI(mkvUri.schemeSpecificPart)
        if (httpUri.scheme !in setOf("http", "https")) {
            throw IllegalArgumentException("Mkv HTTP URI scheme must be 'http' or 'https' not '${httpUri.schemeSpecificPart}'")
        }
        return httpUri.toURL().toString()
    }

    private fun isPreConnectToMasterEnabled(info: Properties?): Boolean {
        info ?: return false
        val preConnectToMaster = info.getProperty(PROP_PRE_CONNECT_TO_MASTER, "false")
        return preConnectToMaster.toBooleanStrict()
    }

    override fun connect(url: String?, info: Properties?): Connection {
        val masterServerUri = validateUri(url)
        val masterConnection = MkvConnection(masterServerUri)
        if (isPreConnectToMasterEnabled(info)) {
            masterConnection.checkMasterConnection()
        }
        return masterConnection
    }

    override fun acceptsURL(url: String?): Boolean {
        validateUri(url)
        return true
    }

    override fun getMajorVersion(): Int {
        return DRIVER_VERSION_MAJOR
    }

    override fun getMinorVersion(): Int {
        return DRIVER_VERSION_MINOR
    }

    override fun jdbcCompliant(): Boolean {
        return false
    }

    override fun getParentLogger(): Logger {
        TODO("Not yet implemented")
    }

    override fun getPropertyInfo(url: String?, info: Properties?): Array<DriverPropertyInfo> {
        TODO("Not yet implemented")
    }

}