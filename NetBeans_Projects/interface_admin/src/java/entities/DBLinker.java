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
        String query = "SELECT GRAVITE,HEURE,ACTION,EQUIPEMENT FROM APP.NOTIFICATIONS WHERE TYPE = 'Chauffage'" ;
        try {
            ResultSet res = state.executeQuery(query); 
            while (!res.isLast()) {
                res.next();
                currentNotif = "--> Le " + res.getString(2) + " : " + res.getString(3)
                    + "<br/><dd> Gravite : " + res.getString(1) + "<br/><dd> Equipement concerné : " +  res.getString(4) + "<br/></dd>";
                tempList.add(currentNotif); 
           }
        } catch (SQLException e) {
            tempList.add("Aucune notification de type Chauffage n'a été trouvée");
        }
        return tempList; 
    }
    
    public List<String> getEclairageNotif() throws SQLException {
        List<String> tempList = new ArrayList<>(); 
        String currentNotif = "";
        
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
       // PreparedStatement prepare 
        String query = "SELECT GRAVITE,HEURE,ACTION,EQUIPEMENT FROM APP.NOTIFICATIONS WHERE TYPE = 'Eclairage'" ;
        try {
            ResultSet res = state.executeQuery(query); 
            while (!res.isLast()) {
                res.next();
                currentNotif = "--> Le " + res.getString(2) + " : " + res.getString(3)
                    + "<br/><dd> Gravite : " + res.getString(1) + "<br/><dd> Equipement concerné : " +  res.getString(4) + "<br/></dd>";
                tempList.add(currentNotif); 
           }
        } catch (SQLException e) {
            tempList.add("Aucune notification de type Eclairage n'a été trouvée ");
        }
        return tempList; 
    }
        
    public List<String> getAllNotif() throws SQLException {
        List<String> tempList = new ArrayList<>(); 
        String currentNotif = "";
        
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
       // PreparedStatement prepare 
        String query = "SELECT GRAVITE,HEURE,ACTION,EQUIPEMENT FROM APP.NOTIFICATIONS" ;
        try {
            ResultSet res = state.executeQuery(query);    
            while (!res.isLast()) {
                res.next();
                currentNotif = "--> Le " + res.getString(2) + " : " + res.getString(3)
                    + "<br/><dd> Gravite : " + res.getString(1) + " <br/><dd>Equipement concerné : " +  res.getString(4) + "<br/></dd>";
                tempList.add(currentNotif); 
           }
        } catch (SQLException e) {
            tempList.add("Aucune notification n'a été trouvée"); 
        }    
        return tempList; 
    }
    
    public String getJSonStringChauffage() {
       return "[ ['zeze','ezetzyt'],['0',051] , ['10',20]]";
     }
    
    public String getJSonStringEau() {
        return "eau";
    }
    
    public String getJSonStringElec() {
        return "elec";
    }
    
    public String getJSonStringVentil() {
        return "ventil";
    }
}
