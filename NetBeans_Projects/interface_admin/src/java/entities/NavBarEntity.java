package entities;

import java.io.Serializable;

/**
 *
 * @author Hugo
 */

public abstract class NavBarEntity implements Serializable
{
    private static String currentURL;
    
    public static String getCurrentURL()
    {
        return NavBarEntity.currentURL;
    }
    
    public static void setCurrentURL(String URL)
    {
        NavBarEntity.currentURL = URL;
    }
}
