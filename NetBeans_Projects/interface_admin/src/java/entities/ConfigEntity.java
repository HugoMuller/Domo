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
    /* Format des HashMap:
     * "heureDebut", "08h00"
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
            con = this.openConnection(con, s, "STANDARD");
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
            con = this.openConnection(con, s, "HOLIDAY");
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
            con = this.openConnection(con, s, "ALERTING");
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
    
    public void setConfigStandard(Map<String, String> configStd)
    {
        loadDriver();
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            con = this.openConnection(con, null, "STANDARD");
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE APP.configurations "
                    + "SET heureDebut=?, heureFin=?, notification=?, "
                    + "sms=?, email=? "
                    + "WHERE mode='STANDARD'");
            ps.setString(1, configStd.get("heureDebut"));
            ps.setString(2, configStd.get("heureFin"));
            ps.setString(3, configStd.get("notification"));
            ps.setString(4, configStd.get("sms"));
            ps.setString(5, configStd.get("email"));
            ps.execute();
            con.commit();
        }
        catch(SQLException sqle)
        {
            printSQLException(sqle);
        }
        finally
        {
            closeConnection(con, null, null, ps);
        }
    }
    
    public void setConfigHoliday(Map<String, String> configStd)
    {
        loadDriver();
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            con = this.openConnection(con, null, "STANDARD");
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE APP.configurations "
                    + "SET heureDebut=?, heureFin=?, notification=?, "
                    + "sms=?, email=? "
                    + "WHERE mode='HOLIDAY'");
            ps.setString(1, configStd.get("heureDebut"));
            ps.setString(2, configStd.get("heureFin"));
            ps.setString(3, configStd.get("notification"));
            ps.setString(4, configStd.get("sms"));
            ps.setString(5, configStd.get("email"));
            ps.execute();
            con.commit();
        }
        catch(SQLException sqle)
        {
            printSQLException(sqle);
        }
        finally
        {
            closeConnection(con, null, null, ps);
        }
    }
    
    public void setConfigAlerting(Map<String, String> configStd)
    {
        loadDriver();
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            con = this.openConnection(con, null, "STANDARD");
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE APP.configurations "
                    + "SET heureDebut=?, heureFin=?, notification=?, "
                    + "sms=?, email=? "
                    + "WHERE mode='ALERTING'");
            ps.setString(1, configStd.get("heureDebut"));
            ps.setString(2, configStd.get("heureFin"));
            ps.setString(3, configStd.get("notification"));
            ps.setString(4, configStd.get("sms"));
            ps.setString(5, configStd.get("email"));
            ps.execute();
            con.commit();
        }
        catch(SQLException sqle)
        {
            printSQLException(sqle);
        }
        finally
        {
            closeConnection(con, null, null, ps);
        }
    }
    
    private Connection openConnection(Connection con, Statement s, String mode) throws SQLException
    {
        //uncomment to add a user and an authenticated connection
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "root");
        return DriverManager.getConnection(AppServletContextListener.protocol + AppServletContextListener.DBname, props);
        /*s = con.createStatement();
        return s.executeQuery("SELECT * "
                + "FROM APP.configurations "
                + "WHERE mode='" + mode + "'");*/
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
