package entities;

import java.io.Serializable;
import mode.ModeType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
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
    
    public static ModeType getMode()
    {
        return ModeEntity.mode;
    }
    
    public static ModeType getDefaultMode()
    {
        return ModeEntity.defaultMode;
    }

    public static void setMode(ModeType mode)
    {
        ModeEntity.mode = mode;
        ModeEntity.fillMode();
    }
    
    public static void fillMode()
    {
        loadDriver();
        Connection con = null;
        Statement s = null;
        try
        {
            String strMode="";
            if(ModeEntity.mode == ModeType.STANDARD)
            {
                strMode = "STANDARD";
            }
            else if(ModeEntity.mode == ModeType.HOLIDAY)
            {
                strMode = "HOLIDAY";
            }
            else if(ModeEntity.mode == ModeType.ALERTING)
            {
                strMode = "ALERTING";
            }
            con = openConnection();
            con.setAutoCommit(false);
            String query = "UPDATE APP.mode"
                + " SET mode='"+strMode+"'"
                + " WHERE id=1";
            s = con.createStatement();
            s.execute(query);
            con.commit();
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
    
    private static Connection openConnection() throws SQLException
    {
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "root");
        return DriverManager.getConnection(AppServletContextListener.protocol + AppServletContextListener.DBname, props);
    }
    
    private static void closeConnection(Connection con, Statement s, ResultSet rs, PreparedStatement ps)
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
