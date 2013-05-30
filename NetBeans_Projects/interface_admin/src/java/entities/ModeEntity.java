package entities;

import java.io.Serializable;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;*/
//import javax.inject.Named;
import mode.ModeType;
/*import servletContextListener.AppServletContextListener;
import static servletContextListener.AppServletContextListener.loadDriver;
import static servletContextListener.AppServletContextListener.printSQLException;*/

/**
 *
 * @author Hugo
 */

//@Named
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
    }
}
