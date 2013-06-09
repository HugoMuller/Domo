package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author YaXi/Elo
 */
public class SingletonConnectionDB {
    private Connection conn;
    private static SingletonConnectionDB mySingleton = null;

    
    private SingletonConnectionDB() throws SQLException {
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String protocol = "jdbc:derby://localhost:1527/";
        String DBname = "EcologU_DB";
        Properties props = new Properties();
        
        props.put("user", "root");
        props.put("password", "root");
        conn = DriverManager.getConnection(protocol + DBname + ";create=true;", props);
    }
    
    public static Connection getInstance() throws SQLException {
         if (mySingleton == null) {
             mySingleton =  new SingletonConnectionDB();
        }
        return mySingleton.conn;
    }
    
}
