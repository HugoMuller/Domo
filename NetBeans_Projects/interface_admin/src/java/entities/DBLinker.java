/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        String query = "SELECT HEURE,GRAVITE,ACTION,EQUIPEMENT FROM APP.NOTIFICATIONS WHERE TYPE = 'Chauffage'" ;
        try {
            ResultSet res = state.executeQuery(query); 
            while (!res.isLast()) {
                res.next();
                currentNotif = res.getString(1) + "%" + res.getString(2)
                    + "%" + res.getString(3) + "%" +  res.getString(4);
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
        String query = "SELECT GRAVITE,HEURE,ACTION,EQUIPEMENT FROM APP.NOTIFICATIONS WHERE TYPE = 'Eclairage'" ;
        try {
            ResultSet res = state.executeQuery(query); 
            while (!res.isLast()) {
                res.next();
                currentNotif = res.getString(1) + "%" + res.getString(2)
                    + "%" + res.getString(3) + "%" +  res.getString(4);
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
        String query = "SELECT GRAVITE,HEURE,ACTION,EQUIPEMENT FROM APP.NOTIFICATIONS" ;
        try {
            ResultSet res = state.executeQuery(query);    
            while (!res.isLast()) {
                res.next();
                currentNotif = res.getString(1) + "%" + res.getString(2)
                    + "%" + res.getString(3) + "%" +  res.getString(4);
                tempList.add(currentNotif); 
           }
        } catch (SQLException e) {
            tempList.add("Aucune notification n'a été trouvée"); 
        }    
        return tempList; 
    }
    
    public String getJSonStringChauffage() throws SQLException {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String results = "[['Heure','Val']"; 
        Date today = new Date();
        SimpleDateFormat formatjour = new SimpleDateFormat("yyyy-MM-dd");
        String jour = formatjour.format(today);
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, -1);
//        Date yesterday = calendar.getTime();
//        String previousJour = formatjour.format(yesterday);
//        
        String query = "SELECT CONSOMMATION, HEURE FROM APP.CHAUFFAGE WHERE NUMCAPTEUR=1 AND JOUR ='" + jour +"' ORDER BY HEURE ASC" ;
       // String previousQuery = "SELECT CONSOMMATION, HEURE FROM APP.CHAUFFAGE WHERE NUMCAPTEUR=1 AND JOUR ='" + previousJour +"' ORDER BY HEURE ASC" ;
        try {
            ResultSet res = state.executeQuery(query);
            while (!res.isLast()){
                res.next();
                results += ",['" + res.getString(2).substring(0,5) + "', " + res.getString(1) + "]";
          }
           results += "]";
        } catch (SQLException e) {
           return "[ ['exception levée','aucun result chauffage'],['8',0] , ['50',0]]";
        }   
        return results;
   }
     
    public String getJSonStringYearChauffage() throws SQLException {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String results = "[['Heure','température']"; 
        Date today = new Date();
        SimpleDateFormat formatjour = new SimpleDateFormat("yyyy-MM-dd");
        String jour = formatjour.format(today);
        SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
        String year = formatyear.format(today);
        String[] temp = null;
        
        String query = "SELECT CONSOMMATION,JOUR FROM APP.CHAUFFAGE WHERE NUMCAPTEUR=1 ORDER BY HEURE ASC" ;
        try {
            ResultSet res = state.executeQuery(query);    
            while (!res.isLast()) {
                res.next();
                temp = res.getString(2).split("-");
                if (temp[0].equals(year)) {
                    results += ", [ '" + res.getString(2) + "', " + res.getString(1) + "]";
                }

           }
           results += "]";
        } catch (SQLException e) {
           return "[ ['exception levée','aucun result chauffage'],['8',0] , ['50',0]]";
        }   
       
       return results;
     }     
    
    public String getJSonStringEau() throws SQLException {
//       Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        String results = "[['Heure','Consommation d'eau]"; 
//        String query = "SELECT CONSOMMATION, HEURE FROM APP.EAU ORDER BY HEURE ASC" ;
//        try {
//            ResultSet res = state.executeQuery(query);    
//            while (!res.isLast()) {
//                res.next();
//                results += ", [ '" + res.getString(2) + "', " + res.getString(1) + "]";
//
//           }
//           results += "]";
//        } catch (SQLException e) {
//           return "[ ['Exception levée','aucun result eau'],['8',0] , ['50',0]]";
//        }   
//        
//       return results;
        return "[ ['Not supported yet','.'],['8',0] , ['50',0]]";
    }
    
    public String getJSonStringElec() {
//       Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        String results = "[['Heure','Consommation Electrique]"; 
//        String query = "SELECT CONSOMMATION, HEURE FROM APP.ELECTRICITE ORDER BY HEURE ASC" ;
//        try {
//            ResultSet res = state.executeQuery(query);    
//            while (!res.isLast()) {
//                res.next();
//                results += ", [ '" + res.getString(2) + "', " + res.getString(1) + "]";
//
//           }
//           results += "]";
//        } catch (SQLException e) {
//           return "[ ['Exception levée','aucun result elec'],['8',0] , ['50',0]]";
//        }   
//        
//       return results;
        return "[ ['Not supported yet','coucou'],['8',70] , ['80',120]]";
    }
    
    public String getJSonStringVentil() {
//       Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        String results = "[['Heure','Vitesse Ventilation]"; 
//        String query = "SELECT CONSOMMATION, HEURE FROM APP.VENTILATIOND ORDER BY HEURE ASC" ;
//        try {
//            ResultSet res = state.executeQuery(query);    
//            while (!res.isLast()) {
//                res.next();
//                results += ", [ '" + res.getString(2) + "', " + res.getString(1) + "]";
//
//           }
//           results += "]";
//        } catch (SQLException e) {
//           return "[ ['Exception levée','aucun result ventil'],['8',0] , ['50',0]]";
//        }   
//        
//       return results;
        return "[ ['Not supported yet','test'],['25',14] , ['800',20]]";
    }
}
