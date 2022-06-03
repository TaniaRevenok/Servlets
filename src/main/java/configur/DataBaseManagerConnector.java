package configur;

import java.sql.Connection;
import java.sql.SQLException;


public interface DataBaseManagerConnector {

 Connection getConnection() throws SQLException;



}
