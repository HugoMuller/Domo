/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import mode.ModeType;

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
    }
}
