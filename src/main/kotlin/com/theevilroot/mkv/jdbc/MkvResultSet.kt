package com.theevilroot.mkv.jdbc

import com.theevilroot.minikeyvaluekt.data.MiniKeyValuePayload
import com.theevilroot.minikeyvaluekt.data.MiniKeyValueQuery
import java.io.InputStream
import java.io.Reader
import java.math.BigDecimal
import java.net.URL
import java.sql.*
import java.sql.Array
import java.sql.Date
import java.util.*

class MkvResultSet(
    private val selectItem: MkvSelectItem,
    private val queries: List<MiniKeyValueQuery>
) : ResultSet {

    private val iterator: Iterator<MiniKeyValueQuery> = queries.iterator()

    private var hitNext: Boolean = false
    private var current: MiniKeyValuePayload? = null

    private fun getCurrent(): MiniKeyValuePayload {
        if (!hitNext) {
            throw IllegalStateException("next() is not called yet")
        }
        return this.current ?: throw IllegalStateException("next() is called but not result is available")
    }

    private inline fun <reified T : Any> getValue(key: String): T? {
        val current = getCurrent()
        return when (key) {
            "key" -> {
                if (T::class.java != String::class.java) {
                    throw IllegalArgumentException("$key is String, not ${T::class.java}")
                }
                current.key as T
            }
            "value" -> {
                current.asString as T
            }
            "volumes" -> {
                if (T::class.java != List::class.java) {
                    throw IllegalArgumentException("$key is List, not ${T::class.java}")
                }
                current.volumes as T
            }
            "hash" -> {
                if (T::class.java != String::class.java) {
                    throw IllegalArgumentException("$key is String, not ${T::class.java}")
                }
                current.contentMd5 as T
            }
            else -> throw SQLException("Column $key is not found")
        }
    }

    override fun <T : Any?> unwrap(p0: Class<T>?): T {
        TODO("Not yet implemented")
    }

    override fun isWrapperFor(p0: Class<*>?): Boolean {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }

    override fun next(): Boolean {
        if (iterator.hasNext()) {
            current = iterator.next().value
            return true
        }
        return false
    }

    override fun wasNull(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getString(p0: Int): String? {
        return getValue(columns[p0 - 1])
    }

    override fun getString(key: String): String? {
        return getString(key)
    }

    override fun getBoolean(p0: Int): Boolean {
        return getValue(columns[p0 - 1]) ?: false
    }

    override fun getBoolean(p0: String): Boolean {
        return getValue(p0) ?: false
    }

    override fun getByte(p0: Int): Byte {
        TODO("Not yet implemented")
    }

    override fun getByte(p0: String?): Byte {
        TODO("Not yet implemented")
    }

    override fun getShort(p0: Int): Short {
        TODO("Not yet implemented")
    }

    override fun getShort(p0: String?): Short {
        TODO("Not yet implemented")
    }

    override fun getInt(p0: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getInt(p0: String?): Int {
        TODO("Not yet implemented")
    }

    override fun getLong(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getLong(p0: String?): Long {
        TODO("Not yet implemented")
    }

    override fun getFloat(p0: Int): Float {
        TODO("Not yet implemented")
    }

    override fun getFloat(p0: String?): Float {
        TODO("Not yet implemented")
    }

    override fun getDouble(p0: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getDouble(p0: String?): Double {
        TODO("Not yet implemented")
    }

    override fun getBigDecimal(p0: Int, p1: Int): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun getBigDecimal(p0: String?, p1: Int): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun getBigDecimal(p0: Int): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun getBigDecimal(p0: String?): BigDecimal {
        TODO("Not yet implemented")
    }

    override fun getBytes(p0: Int): ByteArray {
        TODO("Not yet implemented")
    }

    override fun getBytes(p0: String?): ByteArray {
        TODO("Not yet implemented")
    }

    override fun getDate(p0: Int): Date {
        TODO("Not yet implemented")
    }

    override fun getDate(p0: String?): Date {
        TODO("Not yet implemented")
    }

    override fun getDate(p0: Int, p1: Calendar?): Date {
        TODO("Not yet implemented")
    }

    override fun getDate(p0: String?, p1: Calendar?): Date {
        TODO("Not yet implemented")
    }

    override fun getTime(p0: Int): Time {
        TODO("Not yet implemented")
    }

    override fun getTime(p0: String?): Time {
        TODO("Not yet implemented")
    }

    override fun getTime(p0: Int, p1: Calendar?): Time {
        TODO("Not yet implemented")
    }

    override fun getTime(p0: String?, p1: Calendar?): Time {
        TODO("Not yet implemented")
    }

    override fun getTimestamp(p0: Int): Timestamp {
        TODO("Not yet implemented")
    }

    override fun getTimestamp(p0: String?): Timestamp {
        TODO("Not yet implemented")
    }

    override fun getTimestamp(p0: Int, p1: Calendar?): Timestamp {
        TODO("Not yet implemented")
    }

    override fun getTimestamp(p0: String?, p1: Calendar?): Timestamp {
        TODO("Not yet implemented")
    }

    override fun getAsciiStream(p0: Int): InputStream {
        TODO("Not yet implemented")
    }

    override fun getAsciiStream(p0: String?): InputStream {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getUnicodeStream(p0: Int): InputStream {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun getUnicodeStream(p0: String?): InputStream {
        TODO("Not yet implemented")
    }

    override fun getBinaryStream(p0: Int): InputStream {
        TODO("Not yet implemented")
    }

    override fun getBinaryStream(p0: String?): InputStream {
        TODO("Not yet implemented")
    }

    override fun getWarnings(): SQLWarning {
        TODO("Not yet implemented")
    }

    override fun clearWarnings() {
        TODO("Not yet implemented")
    }

    override fun getCursorName(): String {
        TODO("Not yet implemented")
    }

    override fun getMetaData(): ResultSetMetaData {
        TODO("Not yet implemented")
    }

    override fun getObject(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getObject(p0: String?): Any {
        TODO("Not yet implemented")
    }

    override fun getObject(p0: Int, p1: MutableMap<String, Class<*>>?): Any {
        TODO("Not yet implemented")
    }

    override fun getObject(p0: String?, p1: MutableMap<String, Class<*>>?): Any {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getObject(p0: Int, p1: Class<T>?): T {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getObject(p0: String?, p1: Class<T>?): T {
        TODO("Not yet implemented")
    }

    override fun findColumn(p0: String?): Int {
        TODO("Not yet implemented")
    }

    override fun getCharacterStream(p0: Int): Reader {
        TODO("Not yet implemented")
    }

    override fun getCharacterStream(p0: String?): Reader {
        TODO("Not yet implemented")
    }

    override fun isBeforeFirst(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAfterLast(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isFirst(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isLast(): Boolean {
        TODO("Not yet implemented")
    }

    override fun beforeFirst() {
        TODO("Not yet implemented")
    }

    override fun afterLast() {
        TODO("Not yet implemented")
    }

    override fun first(): Boolean {
        TODO("Not yet implemented")
    }

    override fun last(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getRow(): Int {
        TODO("Not yet implemented")
    }

    override fun absolute(p0: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun relative(p0: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun previous(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setFetchDirection(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getFetchDirection(): Int {
        TODO("Not yet implemented")
    }

    override fun setFetchSize(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun getFetchSize(): Int {
        TODO("Not yet implemented")
    }

    override fun getType(): Int {
        TODO("Not yet implemented")
    }

    override fun getConcurrency(): Int {
        TODO("Not yet implemented")
    }

    override fun rowUpdated(): Boolean {
        TODO("Not yet implemented")
    }

    override fun rowInserted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun rowDeleted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateNull(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun updateNull(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun updateBoolean(p0: Int, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun updateBoolean(p0: String?, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun updateByte(p0: Int, p1: Byte) {
        TODO("Not yet implemented")
    }

    override fun updateByte(p0: String?, p1: Byte) {
        TODO("Not yet implemented")
    }

    override fun updateShort(p0: Int, p1: Short) {
        TODO("Not yet implemented")
    }

    override fun updateShort(p0: String?, p1: Short) {
        TODO("Not yet implemented")
    }

    override fun updateInt(p0: Int, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun updateInt(p0: String?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun updateLong(p0: Int, p1: Long) {
        TODO("Not yet implemented")
    }

    override fun updateLong(p0: String?, p1: Long) {
        TODO("Not yet implemented")
    }

    override fun updateFloat(p0: Int, p1: Float) {
        TODO("Not yet implemented")
    }

    override fun updateFloat(p0: String?, p1: Float) {
        TODO("Not yet implemented")
    }

    override fun updateDouble(p0: Int, p1: Double) {
        TODO("Not yet implemented")
    }

    override fun updateDouble(p0: String?, p1: Double) {
        TODO("Not yet implemented")
    }

    override fun updateBigDecimal(p0: Int, p1: BigDecimal?) {
        TODO("Not yet implemented")
    }

    override fun updateBigDecimal(p0: String?, p1: BigDecimal?) {
        TODO("Not yet implemented")
    }

    override fun updateString(p0: Int, p1: String?) {
        TODO("Not yet implemented")
    }

    override fun updateString(p0: String?, p1: String?) {
        TODO("Not yet implemented")
    }

    override fun updateBytes(p0: Int, p1: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun updateBytes(p0: String?, p1: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun updateDate(p0: Int, p1: Date?) {
        TODO("Not yet implemented")
    }

    override fun updateDate(p0: String?, p1: Date?) {
        TODO("Not yet implemented")
    }

    override fun updateTime(p0: Int, p1: Time?) {
        TODO("Not yet implemented")
    }

    override fun updateTime(p0: String?, p1: Time?) {
        TODO("Not yet implemented")
    }

    override fun updateTimestamp(p0: Int, p1: Timestamp?) {
        TODO("Not yet implemented")
    }

    override fun updateTimestamp(p0: String?, p1: Timestamp?) {
        TODO("Not yet implemented")
    }

    override fun updateAsciiStream(p0: Int, p1: InputStream?, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun updateAsciiStream(p0: String?, p1: InputStream?, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun updateAsciiStream(p0: Int, p1: InputStream?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateAsciiStream(p0: String?, p1: InputStream?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateAsciiStream(p0: Int, p1: InputStream?) {
        TODO("Not yet implemented")
    }

    override fun updateAsciiStream(p0: String?, p1: InputStream?) {
        TODO("Not yet implemented")
    }

    override fun updateBinaryStream(p0: Int, p1: InputStream?, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun updateBinaryStream(p0: String?, p1: InputStream?, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun updateBinaryStream(p0: Int, p1: InputStream?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateBinaryStream(p0: String?, p1: InputStream?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateBinaryStream(p0: Int, p1: InputStream?) {
        TODO("Not yet implemented")
    }

    override fun updateBinaryStream(p0: String?, p1: InputStream?) {
        TODO("Not yet implemented")
    }

    override fun updateCharacterStream(p0: Int, p1: Reader?, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun updateCharacterStream(p0: String?, p1: Reader?, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun updateCharacterStream(p0: Int, p1: Reader?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateCharacterStream(p0: String?, p1: Reader?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateCharacterStream(p0: Int, p1: Reader?) {
        TODO("Not yet implemented")
    }

    override fun updateCharacterStream(p0: String?, p1: Reader?) {
        TODO("Not yet implemented")
    }

    override fun updateObject(p0: Int, p1: Any?, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun updateObject(p0: Int, p1: Any?) {
        TODO("Not yet implemented")
    }

    override fun updateObject(p0: String?, p1: Any?, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun updateObject(p0: String?, p1: Any?) {
        TODO("Not yet implemented")
    }

    override fun insertRow() {
        TODO("Not yet implemented")
    }

    override fun updateRow() {
        TODO("Not yet implemented")
    }

    override fun deleteRow() {
        TODO("Not yet implemented")
    }

    override fun refreshRow() {
        TODO("Not yet implemented")
    }

    override fun cancelRowUpdates() {
        TODO("Not yet implemented")
    }

    override fun moveToInsertRow() {
        TODO("Not yet implemented")
    }

    override fun moveToCurrentRow() {
        TODO("Not yet implemented")
    }

    override fun getStatement(): Statement {
        TODO("Not yet implemented")
    }

    override fun getRef(p0: Int): Ref {
        TODO("Not yet implemented")
    }

    override fun getRef(p0: String?): Ref {
        TODO("Not yet implemented")
    }

    override fun getBlob(p0: Int): Blob {
        TODO("Not yet implemented")
    }

    override fun getBlob(p0: String?): Blob {
        TODO("Not yet implemented")
    }

    override fun getClob(p0: Int): Clob {
        TODO("Not yet implemented")
    }

    override fun getClob(p0: String?): Clob {
        TODO("Not yet implemented")
    }

    override fun getArray(p0: Int): Array {
        TODO("Not yet implemented")
    }

    override fun getArray(p0: String?): Array {
        TODO("Not yet implemented")
    }

    override fun getURL(p0: Int): URL {
        TODO("Not yet implemented")
    }

    override fun getURL(p0: String?): URL {
        TODO("Not yet implemented")
    }

    override fun updateRef(p0: Int, p1: Ref?) {
        TODO("Not yet implemented")
    }

    override fun updateRef(p0: String?, p1: Ref?) {
        TODO("Not yet implemented")
    }

    override fun updateBlob(p0: Int, p1: Blob?) {
        TODO("Not yet implemented")
    }

    override fun updateBlob(p0: String?, p1: Blob?) {
        TODO("Not yet implemented")
    }

    override fun updateBlob(p0: Int, p1: InputStream?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateBlob(p0: String?, p1: InputStream?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateBlob(p0: Int, p1: InputStream?) {
        TODO("Not yet implemented")
    }

    override fun updateBlob(p0: String?, p1: InputStream?) {
        TODO("Not yet implemented")
    }

    override fun updateClob(p0: Int, p1: Clob?) {
        TODO("Not yet implemented")
    }

    override fun updateClob(p0: String?, p1: Clob?) {
        TODO("Not yet implemented")
    }

    override fun updateClob(p0: Int, p1: Reader?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateClob(p0: String?, p1: Reader?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateClob(p0: Int, p1: Reader?) {
        TODO("Not yet implemented")
    }

    override fun updateClob(p0: String?, p1: Reader?) {
        TODO("Not yet implemented")
    }

    override fun updateArray(p0: Int, p1: Array?) {
        TODO("Not yet implemented")
    }

    override fun updateArray(p0: String?, p1: Array?) {
        TODO("Not yet implemented")
    }

    override fun getRowId(p0: Int): RowId {
        TODO("Not yet implemented")
    }

    override fun getRowId(p0: String?): RowId {
        TODO("Not yet implemented")
    }

    override fun updateRowId(p0: Int, p1: RowId?) {
        TODO("Not yet implemented")
    }

    override fun updateRowId(p0: String?, p1: RowId?) {
        TODO("Not yet implemented")
    }

    override fun getHoldability(): Int {
        TODO("Not yet implemented")
    }

    override fun isClosed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateNString(p0: Int, p1: String?) {
        TODO("Not yet implemented")
    }

    override fun updateNString(p0: String?, p1: String?) {
        TODO("Not yet implemented")
    }

    override fun updateNClob(p0: Int, p1: NClob?) {
        TODO("Not yet implemented")
    }

    override fun updateNClob(p0: String?, p1: NClob?) {
        TODO("Not yet implemented")
    }

    override fun updateNClob(p0: Int, p1: Reader?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateNClob(p0: String?, p1: Reader?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateNClob(p0: Int, p1: Reader?) {
        TODO("Not yet implemented")
    }

    override fun updateNClob(p0: String?, p1: Reader?) {
        TODO("Not yet implemented")
    }

    override fun getNClob(p0: Int): NClob {
        TODO("Not yet implemented")
    }

    override fun getNClob(p0: String?): NClob {
        TODO("Not yet implemented")
    }

    override fun getSQLXML(p0: Int): SQLXML {
        TODO("Not yet implemented")
    }

    override fun getSQLXML(p0: String?): SQLXML {
        TODO("Not yet implemented")
    }

    override fun updateSQLXML(p0: Int, p1: SQLXML?) {
        TODO("Not yet implemented")
    }

    override fun updateSQLXML(p0: String?, p1: SQLXML?) {
        TODO("Not yet implemented")
    }

    override fun getNString(p0: Int): String {
        TODO("Not yet implemented")
    }

    override fun getNString(p0: String?): String {
        TODO("Not yet implemented")
    }

    override fun getNCharacterStream(p0: Int): Reader {
        TODO("Not yet implemented")
    }

    override fun getNCharacterStream(p0: String?): Reader {
        TODO("Not yet implemented")
    }

    override fun updateNCharacterStream(p0: Int, p1: Reader?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateNCharacterStream(p0: String?, p1: Reader?, p2: Long) {
        TODO("Not yet implemented")
    }

    override fun updateNCharacterStream(p0: Int, p1: Reader?) {
        TODO("Not yet implemented")
    }

    override fun updateNCharacterStream(p0: String?, p1: Reader?) {
        TODO("Not yet implemented")
    }
}