package entities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import javax.inject.Named;
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
    private Map<String, String> configStandard = new HashMap<>();
    private Map<String, String> configHoliday  = new HashMap<>();
    private Map<String, String> configAlerting = new HashMap<>();
    /* Format des HashMap:
     * "heureDebut", "08h00"
     * "heureFin", "18h00"
     * "notification", "sms" ou "email" ou "both"
     * "sms", "0123456789" ou ""
     * "email", "le-gars@la-boite.fr" ou ""
    */
    private static final Logger LOG = Logger.getLogger(ConfigEntity.class.getName());
    
    public Map<String, String> getConfigStandard()
    {
        loadDriver();
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        try
        {
            con = this.openConnection();
            s = con.createStatement();
            rs = s.executeQuery("SELECT * "
                + "FROM APP.configurations "
                + "WHERE mode='STANDARD'");
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
            closeConnection(con, s, rs, null);
        }
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
            con = this.openConnection();
            s = con.createStatement();
            rs = s.executeQuery("SELECT * "
                + "FROM APP.configurations "
                + "WHERE mode='HOLIDAY'");
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
            closeConnection(con, s, rs, null);
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
            con = this.openConnection();
            s = con.createStatement();
            rs = s.executeQuery("SELECT * "
                + "FROM APP.configurations "
                + "WHERE mode='ALERTING'");
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
            closeConnection(con, s, rs, null);
        }
        return this.configAlerting;
    }
    
    public void setConfigStandard(Map<String, String> config)
    {
        loadDriver();
        Connection con = null;
        Statement s = null;
        try
        {
            con = this.openConnection();
            con.setAutoCommit(false);
            for(Map.Entry entry : config.entrySet())
            {
                String query = "UPDATE APP.configurations "
                    + "SET valeur='"+(String)entry.getValue()+"'"
                    + " WHERE mode='STANDARD' AND attribut='"+(String)entry.getKey()+"'";
                s = con.createStatement();
                s.execute(query);
                con.commit();
            }
        }
        catch(SQLException sqle)
        {
            printSQLException(sqle);
        }
        finally
        {
            closeConnection(con, s, null, null);
        }
    }
    
    public void setConfigHoliday(Map<String, String> config)
    {
        loadDriver();
        Connection con = null;
        Statement s = null;
        try
        {
            con = this.openConnection();
            con.setAutoCommit(false);
            for(Map.Entry entry : config.entrySet())
            {
                String query = "UPDATE APP.configurations "
                    + "SET valeur='"+(String)entry.getValue()+"'"
                    + " WHERE mode='HOLIDAY' AND attribut='"+(String)entry.getKey()+"'";
                s = con.createStatement();
                s.execute(query);
                con.commit();
            }
        }
        catch(SQLException sqle)
        {
            printSQLException(sqle);
        }
        finally
        {
            closeConnection(con, s, null, null);
        }
    }
    
    public void setConfigAlerting(Map<String, String> config)
    {
        loadDriver();
        Connection con = null;
        Statement s = null;
        try
        {
            con = this.openConnection();
            con.setAutoCommit(false);
            for(Map.Entry entry : config.entrySet())
            {
                String query = "UPDATE APP.configurations "
                    + "SET valeur='"+(String)entry.getValue()+"'"
                    + " WHERE mode='ALERTING' AND attribut='"+(String)entry.getKey()+"'";
                s = con.createStatement();
                s.execute(query);
                con.commit();
            }
        }
        catch(SQLException sqle)
        {
            printSQLException(sqle);
        }
        finally
        {
            closeConnection(con, s, null, null);
        }
    }
    
    private Connection openConnection() throws SQLException
    {
        //uncomment to add a user and an authenticated connection
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "root");
        return DriverManager.getConnection(AppServletContextListener.protocol + AppServletContextListener.DBname, props);
    }
    
    private void closeConnection(Connection con, Statement s, ResultSet rs, PreparedStatement ps)
    {
        try
        {
            if(ps != null)
            {
                ps.close();
                ps = null;
            }
        }
        catch(SQLException sqle)   {printSQLException(sqle);}
        
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
