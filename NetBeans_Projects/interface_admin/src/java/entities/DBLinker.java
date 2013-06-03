/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        List<String> tempList = new ArrayList<>(); 
        String currentNotif = "";
        
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
       // PreparedStatement prepare 
        String query = "SELECT GRAVITE,HEURE,ACTION,EQUIPEMENT FROM APP.NOTIFICATIONS" ;
        ResultSet res = state.executeQuery(query); 
        
        while (!res.isLast()) {
            res.next();
            currentNotif+= "Le " + res.getString(2) + " : " + res.getString(3)
                + "<br/><dd> Gravite : " + res.getString(1) + " Equipement concerné : " +  res.getString(4) + "<br/></dd>";
            tempList.add(currentNotif);  
        }
        
        return tempList; 
    }
    
}
