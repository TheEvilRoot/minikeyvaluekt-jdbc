import com.theevilroot.mkv.jdbc.MkvDriver
import org.junit.Test
import java.sql.DriverManager

class SimpleConnect {

    @Test
    fun `Create connection with driver manager with default info props`() {
        DriverManager.registerDriver(MkvDriver())
        DriverManager.getConnection("jdbc:mkv:http://localhost:3000")
    }

    @Test
    fun `Statement select key from master where key like 'EVENT_'`() {
        DriverManager.registerDriver(MkvDriver())
        val connection = DriverManager.getConnection("jdbc:mkv:http://localhost:3000")
        val statement = connection.createStatement()
        assert(statement.execute("select key from master where key like 'EVENT_%'"))
        val resultSet = statement.resultSet
        while (resultSet.next()) {
            println(resultSet.getString("key"))
            println(resultSet.getString("value"))
        }
    }
    @Test
    fun `Prepared statement select key from master`() {
        DriverManager.registerDriver(MkvDriver())
        val connection = DriverManager.getConnection("jdbc:mkv:http://localhost:3000")
        connection.prepareStatement("select key from master").executeQuery()
    }
}
