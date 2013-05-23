package servletContextListener;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
//import org.apache.derby.tools.ij;

/**
 *
 * @author Hugo
 */
@WebListener
public class AppServletContextListener implements ServletContextListener
{
    private String framework = "derbyclient"; //"embedded";
    private String driver = "org.apache.derby.jdbc.ClientDriver"; //"org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby://localhost:1527/"; //"jdbc:derby:";
    private String DBname = "EcologU_DB";
    
    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        System.out.println("ServletContextListener started");
        this.setUpDB();
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        System.out.println("ServletContextListener destroyed");
    }
    
    private void setUpDB()
    {
        this.loadDriver();
        Connection con = null;
        ArrayList<Statement> statements = new ArrayList<>();
        Statement s = null;

        try
        {
            //uncomment to add a user and an authenticated connection
            Properties props = new Properties();
            props.put("user", "root");
            props.put("password", "root");
            con = DriverManager.getConnection(protocol + DBname + ";create=true;", props);
            con.setAutoCommit(false);
            s = con.createStatement();
            statements.add(s);
            s.execute("CREATE TABLE heating("
                        + "heure date not null primary key,"
                        + "consommation double"
                        + ");");
            System.out.println("Création de la table 'heating'");

            s.execute("CREATE TABLE light("
                        + "heure date not null primary key,"
                        + "consommation double"
                        + ");");
            System.out.println("Création de la table 'light'");
            
            s.execute("CREATE TABLE kikoulol("
                        + "kikou int primary key,"
                        + "trololol double"
                        + ");");
            System.out.println("Création de la table 'kikoulol'");
            
            con.commit();
            System.out.println("Transactions faites");
        }
        catch(SQLException sqle)
        {
            printSQLException(sqle);
            if("01J01".equals(sqle.getSQLState()))
            {
                System.out.println(sqle.getMessage());
            }
            else
            {
                System.out.println("Création de la DB " + DBname);
                System.out.println("Connection à la DB " + DBname);
            }
        }
        finally
        {
            while(!statements.isEmpty())
            {
                Statement st = (Statement) statements.remove(0);
                try
                {
                    if(st != null)
                    {
                        st.close();
                        st = null;
                    }
                }
                catch(SQLException sqle)
                {
                    printSQLException(sqle);
                }
            }
            
            try
            {
                if (con != null)
                {
                    con.close();
                    con = null;
                }
            }
            catch(SQLException sqle)
            {
                printSQLException(sqle);
            }
        }
    }
    
    private void loadDriver()
    {
        try
        {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
        }
        catch(ClassNotFoundException cnfe)
        {
            System.err.println("\nUnable to load the JDBC driver " + driver);
            System.err.println("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        }
        catch(InstantiationException ie)
        {
            System.err.println("\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        }
        catch(IllegalAccessException iae)
        {
            System.err.println("\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }
    
    private void reportFailure(String message)
    {
        System.err.println("\nData verification failed:");
        System.err.println('\t' + message);
    }
    
    public static void printSQLException(SQLException e)
    {
        while(e != null)
        {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }
}
