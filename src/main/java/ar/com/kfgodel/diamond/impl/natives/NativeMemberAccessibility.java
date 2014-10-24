package ar.com.kfgodel.diamond.impl.natives;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.lang.reflect.AccessibleObject;

/**
 * This type is a helper class to enforce field accessibility
 * Created by kfgodel on 23/10/14.
 */
public class NativeMemberAccessibility {

    /**
     * Forces the accessibility to the given object changing it if needed
     * @param accessibleObject The object to make accessible
     */
    public static void ensuredFor(AccessibleObject accessibleObject){
        try {
            if(!accessibleObject.isAccessible()){
                accessibleObject.setAccessible(true);
            }
        } catch (SecurityException e) {
            throw new DiamondException("Security restrictions prevent us from accessing["+accessibleObject+"]",e);
        }
    }
}
