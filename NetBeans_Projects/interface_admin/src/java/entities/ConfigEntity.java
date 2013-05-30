package entities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import mode.ModeType;
import servlet.ModeServlet;
import servletContextListener.AppServletContextListener;
import static servletContextListener.AppServletContextListener.loadDriver;
import static servletContextListener.AppServletContextListener.printSQLException;

/**
 *
 * @author Hugo
 */

@Named
public class ConfigEntity implements Serializable
{
    private Map<String, String> configStandard = new HashMap<String, String>();
    private Map<String, String> configHoliday  = new HashMap<String, String>();
    private Map<String, String> configAlerting = new HashMap<String, String>();
    
    private static final Logger LOG = Logger.getLogger(ModeServlet.class.getName());
    /* Format des HashMap:
     * "heureDebut", "8h00"
     * "heureFin", "18h00"
     * "notification", "sms" ou "email" ou "both"
     * "sms", "0123456789" ou ""
     * "email", "le-gars@la-boite.fr" ou ""
    */
    
    public Map<String, String> getConfigStandard()
    {
        loadDriver();
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        try
        {
            //uncomment to add a user and an authenticated connection
            rs = openConnection(con, s, "STANDARD");
            while(rs.next())
            {
                this.configStandard.put(rs.getString("attribut"), rs.getString("valeur"));
            } 
        }
        catch(SQLException sqle)
        {
            printSQLException(sqle);
        }
        finally
        {
            closeConnection(con, s, rs);
        }
        /*LOG.log(Level.INFO, "================================================ getConfigStandard DONE ================================================");
        Iterator iterator = configStandard.keySet().iterator();  
        while (iterator.hasNext()) {  
           String key = iterator.next().toString();  
           String value = configStandard.get(key).toString();  

           LOG.log(Level.INFO, key + " " + value);  
        } */ 
        return this.configStandard;
    }
    
    public  Map<String, String> getConfigHoliday()
    {
        loadDriver();
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        try
        {
            //uncomment to add a user and an authenticated connection
            rs = openConnection(con, s, "HOLIDAY");
            while(rs.next())
            {
                this.configHoliday.put(rs.getString("attribut"), rs.getString("valeur"));
            } 
        }
        catch(SQLException sqle)
        {
            printSQLException(sqle);
        }
        finally
        {
            closeConnection(con, s, rs);
        }
        return this.configHoliday;
    }
    
    public Map<String, String> getConfigAlerting()
    {
        loadDriver();
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        try
        {
            //uncomment to add a user and an authenticated connection
            rs = openConnection(con, s, "ALERTING");
            while(rs.next())
            {
                this.configAlerting.put(rs.getString("attribut"), rs.getString("valeur"));
            } 
        }
        catch(SQLException sqle)
        {
            printSQLException(sqle);
        }
        finally
        {
            closeConnection(con, s, rs);
        }
        return this.configAlerting;
    }
    
    private ResultSet openConnection(Connection con, Statement s, String mode) throws SQLException
    {
        //uncomment to add a user and an authenticated connection
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "root");
        con = DriverManager.getConnection(AppServletContextListener.protocol + AppServletContextListener.DBname, props);
        s = con.createStatement();
        return s.executeQuery("SELECT * "
                + "FROM APP.configurations "
                + "WHERE mode='" + mode + "'");
    }
    
    private void closeConnection(Connection con, Statement s, ResultSet rs)
    {
        try
        {
            if(rs != null)
            {
                rs.close();
                rs = null;
            }
        }
        catch(SQLException sqle)   {printSQLException(sqle);}
        
        try
        {
            if(s != null)
            {
                s.close();
                s = null;
            }
        }
        catch(SQLException sqle)    {printSQLException(sqle);}
            
        try
        {
            if(con != null)
            {
                con.close();
                con = null;
            }
        }
        catch (SQLException sqle)   {printSQLException(sqle);}
    }
}
