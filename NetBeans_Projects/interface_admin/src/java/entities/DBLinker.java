/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author YaXi/Elo
 */
public class DBLinker {
    Connection conn;
   
    public DBLinker() throws SQLException {
        conn = SingletonConnectionDB.getInstance();
    }
    
    public List<String> getChauffageNotif() throws SQLException {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
        
        return null; 
    }
    
}
