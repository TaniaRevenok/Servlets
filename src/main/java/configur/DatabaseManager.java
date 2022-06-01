package configur;
import java.sql.SQLException;
import java.sql.Connection;

public interface DatabaseManager {
 Connection getConnection() throws SQLException;

}

