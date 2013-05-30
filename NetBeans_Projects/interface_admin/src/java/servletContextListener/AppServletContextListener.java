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
    private ArrayList<String> tables = new ArrayList<String>()
            {
                {
                    add("chauffage");
                    add("eclairage");
                    add("eau");
                    add("ventillation");
                }
            };
    
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
        loadDriver();
        Connection con = null;
        Statement s = null;
        
        try
        {
            //uncomment to add a user and an authenticated connection
            Properties props = new Properties();
            props.put("user", "root");
            props.put("password", "root");
            System.out.println("Création de la base '" + DBname + "'");
            con = DriverManager.getConnection(protocol + DBname + ";create=true;", props);
            con.setAutoCommit(false);
            s = con.createStatement();
            s.execute("SET SCHEMA APP");
        }
        catch(SQLException sqle)
        {
            printSQLException(sqle);
        }
        finally
        {
            for(int i=0; i<tables.size(); i++)
            {
                try
                {
                    s.execute("CREATE TABLE APP." + (String)this.tables.get(i)
                            + "(heure date not null primary key,"
                            + "consommation double)");
                    con.commit();
                }
                catch(SQLException sqle)
                {
                    if(!(sqle.getErrorCode() == -1 && "X0Y32".equals(sqle.getSQLState())))
                    {
                        printSQLException(sqle);
                    }
                }
                System.out.println("Création de la table '" +  this.tables.get(i) + "'");
            }
            
            try
            {
                s.execute("CREATE TABLE APP.configurations"
                        + "(mode char(10) not null,"
                        + "attribut char(20) not null,"
                        + "valeur varchar(60),"
                        + "primary key(mode, attribut))");
                con.commit();
            }
            catch(SQLException sqle)
            {
                if(!(sqle.getErrorCode() == -1 && "X0Y32".equals(sqle.getSQLState())))
                    {
                        printSQLException(sqle);
                    }
                System.out.println("Création de la table 'configurations'");
            }
            
            //penser à ajouter ce try dans le code d'alex
            try
            {
                fillConfig(con, "STANDARD", "heureDebut", "8h00");
                fillConfig(con, "STANDARD", "heureFin", "18h00");
                fillConfig(con, "STANDARD", "notification", "both");
                fillConfig(con, "STANDARD", "sms", "");
                fillConfig(con, "STANDARD", "email", "le-gars@la-boite.fr");
                
                fillConfig(con, "HOLIDAY", "heureDebut", "10h00");
                fillConfig(con, "HOLIDAY", "heureFin", "16h00");
                fillConfig(con, "HOLIDAY", "notification", "email");
                fillConfig(con, "HOLIDAY", "sms", "");
                fillConfig(con, "HOLIDAY", "email", "un-autre-gars@la-boite.fr");
                
                fillConfig(con, "ALERTING", "heureDebut", "8h00");
                fillConfig(con, "ALERTING", "heureFin", "18h00");
                fillConfig(con, "ALERTING", "notification", "both");
                fillConfig(con, "ALERTING", "sms", "0668371432");
                fillConfig(con, "ALERTING", "email", "le-gars-a-eduquer-a-donf@la-boite.fr");
                con.commit();
            }
            catch(SQLException sqle)
            {
                printSQLException(sqle);
            }
            
            try
            {
                System.out.println("Affichage des tables");
                ResultSet st = s.executeQuery("SELECT tablename "
                        + "FROM sys.systables "
                        + "WHERE tablename NOT LIKE 'SYS%'");
                while(st.next())
                {
                    System.out.println(st.getString("TABLENAME"));
                }
                System.out.println("\n\n Affichage de la table 'configurations'");
                st = s.executeQuery("SELECT * FROM APP.configurations");
                con.commit();
                while(st.next())
                {
                    System.out.println("[ "+st.getString("mode")+", "
                            + st.getString("attribut") + ", "
                            + st.getString("valeur") + " ]");
                }
                if(s != null)
                {
                    s.close();
                    s = null;
                }
            }
            catch(SQLException sqle)
            {
                printSQLException(sqle);
            }
            finally
            {
                try
                {
                    if(con != null)
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
        con.commit();
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
