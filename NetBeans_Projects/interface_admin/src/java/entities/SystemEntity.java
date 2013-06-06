/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.inject.Named;

/**
 *
 * @author Hugo
 */

@Named
public abstract class SystemEntity implements Serializable
{
    private static boolean isOff = false;
    private static boolean[] ledOn = {true, true};
    
    public static boolean isOff()
    {
        return SystemEntity.isOff;
    }
    
    public static void setOff(boolean state)
    {
        SystemEntity.isOff = state;
    }
    
    public static boolean getLed(int n)
    {
        return SystemEntity.ledOn[n-2];
    }
    
    public static void setLed(int n)
    {
        SystemEntity.ledOn[n-2] = !SystemEntity.ledOn[n-2];
    }
}
