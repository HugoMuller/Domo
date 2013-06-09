package servletContextListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Hugo
 */
@WebListener
public class AppServletContextListener implements ServletContextListener
{
    public static String framework = "derbyclient"; //"embedded";
    public static String driver = "org.apache.derby.jdbc.ClientDriver"; //"org.apache.derby.jdbc.EmbeddedDriver";
    public static String protocol = "jdbc:derby://localhost:1527/"; //"jdbc:derby:";
    public static String DBname = "EcologU_DB";
    
    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        System.out.println("ServletContextListener started");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        System.out.println("ServletContextListener destroyed");
    }
    
    public static void loadDriver()
    {
        try
        {
            Class.forName(driver).newInstance();
            System.out.println(driver + " chargé");
        }
        catch(ClassNotFoundException cnfe)
        {
            System.err.println("\nImpossible de charger le JDBC driver " + driver);
            System.err.println("Vérifiez votre CLASSPATH.");
            cnfe.printStackTrace(System.err);
        }
        catch(InstantiationException ie)
        {
            System.err.println("\nImpossible d'instantier le JDBC driver " + driver);
            ie.printStackTrace(System.err);
        }
        catch(IllegalAccessException iae)
        {
            System.err.println("\nAccès non autorisé au JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }
    
    public static void fillConfig(Connection con, String mode, String attr, String value) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement("INSERT INTO APP.configurations "
                + "values(?, ?, ?)");
        ps.setString(1, mode);
        ps.setString(2, attr);
        ps.setString(3, value);
        ps.execute();
        //con.commit();
    }
    
    public static void updateConfig(Connection con, String mode, String attr, String value) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement("UPDATE APP.configurations"
                + "set valeur=? where mode=? and attribut=?");
        ps.setString(1, value);
        ps.setString(2, mode);
        ps.setString(3, attr);
        ps.execute();
    }
    
    private void reportFailure(String message)
    {
        System.err.println("\nEchec de la vérification des données:");
        System.err.println('\t' + message);
    }
    
    public static void printSQLException(SQLException sqle)
    {
        while(sqle != null)
        {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + sqle.getSQLState());
            System.err.println("  Error Code: " + sqle.getErrorCode());
            System.err.println("  Message:    " + sqle.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            //sqle.printStackTrace(System.err);
            sqle = sqle.getNextException();
        }
    }
}
