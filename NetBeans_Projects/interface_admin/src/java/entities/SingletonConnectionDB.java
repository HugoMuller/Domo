/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private volatile static Connection conn;
    
    private SingletonConnectionDB() throws SQLException {
        String driver = "org.apache.derby.jdbc.ClientDriver"; //"org.apache.derby.jdbc.EmbeddedDriver";
        String protocol = "jdbc:derby://localhost:1527/"; //"jdbc:derby:";
        String DBname = "EcologU_DB";
        Properties props = new Properties();
        
        props.put("user", "root");
        props.put("password", "root");
        conn = DriverManager.getConnection(protocol + DBname + ";create=true;", props);
    }
    
    public static Connection getInstance() throws SQLException {
        if (conn == null) {
            conn = (Connection) new SingletonConnectionDB();
        }
        return conn;
    }
    
}
