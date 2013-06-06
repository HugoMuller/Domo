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
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
        String results = "[['Heure','Today']"; 
        Date today = new Date();
        SimpleDateFormat formatjour = new SimpleDateFormat("yyyy-MM-dd");
        String jour = formatjour.format(today);
      
        String query = "SELECT CONSOMMATION, HEURE FROM APP.CHAUFFAGE WHERE NUMCAPTEUR=1 AND JOUR ='" + jour +"' ORDER BY HEURE ASC" ;
        try {
            ResultSet res = state.executeQuery(query);
            while (!res.isLast()) {
                    res.next();
                    results += ",['" + res.getString(2).substring(0,5) + "', " + res.getString(1)+ "]";
             }
             results += "]";
         } catch (SQLException e) {
           return e.getMessage();
        }
        return results;
   }
        
    public String getJSonStringTodayChauffage() throws SQLException {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
        String results = "["; 
        Date today = new Date();
        SimpleDateFormat formatjour = new SimpleDateFormat("yyyy-MM-dd");
        String jour = formatjour.format(today);
        
        String query = "SELECT CONSOMMATION, HEURE FROM APP.CHAUFFAGE WHERE NUMCAPTEUR=1 AND JOUR ='" + jour +"' ORDER BY HEURE ASC" ;
         try {
            ResultSet res = state.executeQuery(query);
            while (!res.isLast()) {
                    res.next();
                    if (!res.isFirst()) {
                         results += ",[" + this.getFloatFromTimeString(res.getString(2).substring(0,5)) + ", " + res.getString(1)+ "]";
                    } else {
                        results += "[" + this.getFloatFromTimeString(res.getString(2).substring(0,5)) + ", " + res.getString(1)+ "]";
                    }      
             }
             results += "]";
         } catch (SQLException e) {
           return e.getMessage();
        }
        return results;
   }
    
    public String getJSonStringYesterdayChauffage() throws SQLException {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
        String results = "["; 
        Date today = new Date();
        SimpleDateFormat formatjour = new SimpleDateFormat("yyyy-MM-dd");
        String jour = formatjour.format(today);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        String previousJour = formatjour.format(yesterday);
      
        String query = "SELECT CONSOMMATION, HEURE FROM APP.CHAUFFAGE WHERE NUMCAPTEUR=1 AND JOUR ='" + previousJour +"' ORDER BY HEURE ASC" ;
        try {
            ResultSet res = state.executeQuery(query);
            while (!res.isLast()) {
                    res.next();
                    if (!res.isFirst()) {
                         results += ",[" + this.getFloatFromTimeString(res.getString(2).substring(0,5)) + ", " + res.getString(1)+ "]";
                    } else {
                        results += "[" + this.getFloatFromTimeString(res.getString(2).substring(0,5)) + ", " + res.getString(1)+ "]";
                    }      
             }
             results+= "]";
         } catch (SQLException e) {
           return e.getMessage();
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
        
        String query = "SELECT CONSOMMATION,JOUR FROM APP.CHAUFFAGE WHERE NUMCAPTEUR=1 ORDER BY JOUR ASC" ;
        try {
            ResultSet res = state.executeQuery(query);    
            while (!res.isLast()) {
                res.next();
                temp = res.getString(2).split("-");
                if (temp[0].equals(year)) {
                    results += ", [ '" + temp[2] +"/" +temp[1] + "', " + res.getString(1) + "]";
                }

           }
           results += "]";
        } catch (SQLException e) {
           return "[ ['exception levée','aucun result chauffage'],['8',0] , ['50',0]]";
        }   
       
       return results +"%" +temp[0];
     }     
    
    public String getJSonStringEau() throws SQLException {
        return "[ ['Not supported yet','Static'],['8',0] , ['50',0]]";
    }
    
    public String getJSonStringElec() throws SQLException {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String results = "[['Heure','Val']"; 

        
        String query = "SELECT CONSOMMATION, HEURE FROM APP.ELECTRICITE ORDER BY HEURE ASC" ;
        try {
            ResultSet res = state.executeQuery(query);
            while (!res.isLast()){
                res.next();
                String[] temp = res.getString(2).split(" ");
                results += ",['" + temp[4] + "', " + res.getString(1) + "]";
          }
           results += "]";
        } catch (SQLException e) {
           return "[ ['exception levée','aucun result chauffage'],['8',0] , ['50',0]]";
        }   
        return results;
    }
    
    public String getJSonStringVentil() {
        return "[ ['Not supported yet','Static'],['25',14] , ['800',20]]";
    }
    
    public float getFloatFromTimeString(String time) {
        String[] temp = time.split(":");
        return Float.parseFloat(temp[0]) + Float.parseFloat(temp[1])/60 ; 
    }
}
