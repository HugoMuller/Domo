package entities;

import java.io.Serializable;

/**
 *
 * @author Hugo
 */

public abstract class SystemEntity implements Serializable
{
    private static boolean isOff = false;
    private static boolean[] ledOn = {true, true};
    private static float Tmin = 25;
    private static float Tmax = 27;
    
    public static boolean isOff()
    {
        return SystemEntity.isOff;
    }
    
    public static boolean getLed(int n)
    {
        return SystemEntity.ledOn[n-2];
    }
    
    public static float getTmin()
    {
        return SystemEntity.Tmin;
    }
    
    public static float getTmax()
    {
        return SystemEntity.Tmax;
    }
    
    public static void setOff(boolean state)
    {
        SystemEntity.isOff = state;
    }
    
    public static void setLed(int n)
    {
        SystemEntity.ledOn[n-2] = !SystemEntity.ledOn[n-2];
    }
    
    public static void setTmin(float Tmin)
    {
        SystemEntity.Tmin = Tmin;
    }
    
    public static void setTmax(float Tmax)
    {
        SystemEntity.Tmax = Tmax;
    }
}
