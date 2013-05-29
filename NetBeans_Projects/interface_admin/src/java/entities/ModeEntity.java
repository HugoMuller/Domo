package entities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import mode.ModeType;
import servletContextListener.AppServletContextListener;
import static servletContextListener.AppServletContextListener.loadDriver;
import static servletContextListener.AppServletContextListener.printSQLException;

/**
 *
 * @author Hugo
 */

public abstract class ModeEntity implements Serializable
{
    private static ModeType mode = ModeType.STANDARD;
    private static ModeType defaultMode = ModeType.STANDARD;
    private static Map<String, String> configStandard = new HashMap<String, String>();
    private static Map<String, String> configHoliday  = new HashMap<String, String>();
    private static Map<String, String> configAlerting = new HashMap<String, String>();
    
    public static ModeType getMode()
    {
        return ModeEntity.mode;
    }
    
    public static ModeType getDefaultMode()
    {
        return ModeEntity.defaultMode;
    }
    
    public static Map<String, String> getConfigStandard()
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
                configStandard.put(rs.getString("attribute"), rs.getString("value"));
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
        return configStandard;
    }
    
    public static Map<String, String> getConfigHoliday()
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
                configHoliday.put(rs.getString("attribute"), rs.getString("value"));
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
        return configHoliday;
    }
    
    public static Map<String, String> getConfigAlerting()
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
                configAlerting.put(rs.getString("attribute"), rs.getString("value"));
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
        return configAlerting;
    }
    
    public static void setMode(ModeType mode)
    {
        ModeEntity.mode = mode;
    }
    
    private static ResultSet openConnection(Connection con, Statement s, String mode) throws SQLException
    {
        //uncomment to add a user and an authenticated connection
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "root");
        con = DriverManager.getConnection(AppServletContextListener.protocol + AppServletContextListener.DBname, props);
        s = con.createStatement();
        return s.executeQuery("SELECT * "
                + "FROM APP.configurations "
                + "WHERE mode=\"" + mode + "\"");
    }
    
    private static void closeConnection(Connection con, Statement s, ResultSet rs)
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
