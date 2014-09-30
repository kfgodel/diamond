package ar.com.kfgodel.diamond.impl.generics;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.lang.reflect.*;

/**
 * This type knows how to extract the raw class from its generified version.<br>
 *     This fragment is specially needed for generic arrays, and is taken from: http://www.java2s.com/Code/Java/Reflection/GetRawClass.htm
 * Created by kfgodel on 21/09/14.
 */
public class RawClassExtractor {

    /**
     * Extracts the raw type from the given parameterized type.<br>
     *  It's raw type is accesible from it directly but we cannot ascertain that it's a class
     * @param parameterizedType The parameterized type
     * @return The class instance for that type
     */
    public static Class<?> from(ParameterizedType parameterizedType){
        return fromUnspecific(parameterizedType.getRawType());
    }

    /**
     * Extracts the raw type from the given generic array type.<br>
     *  This method creates a new array instance to get the actual raw class from the VM.<br>
     *  There's no other way to get an array class than from an instance
     * @param genericArrayType The generic type
     * @return The class instance
     */
    public static Class<?> from(GenericArrayType genericArrayType){
        Class<?> rawComponentClass = fromUnspecific(genericArrayType.getGenericComponentType());
        return Array.newInstance(rawComponentClass, 0).getClass();
    }

    /**
     * Generic type extractor method for unspecific Type instances.<br>
     * This method can get called recursively from generified types
     * @param type The instance to degenerify
     * @return The raw class
     */
    public static Class<?> fromUnspecific(final Type type) {
        if (Class.class.isInstance(type)) {
            return Class.class.cast(type);
        }
        if (ParameterizedType.class.isInstance(type)) {
            return from(ParameterizedType.class.cast(type));
        }
        if (GenericArrayType.class.isInstance(type)) {
            GenericArrayType genericArrayType = GenericArrayType.class
                    .cast(type);
            Class<?> componentType = fromUnspecific(genericArrayType.getGenericComponentType());
            return Array.newInstance(componentType, 0).getClass();
        }
        if(WildcardType.class.isInstance(type)){
            WildcardType wildcardType = WildcardType.class.cast(type);
            return deduceTypeFromUpperBounds(wildcardType.getUpperBounds());
        }
        if(TypeVariable.class.isInstance(type)){
            TypeVariable typeVariable = TypeVariable.class.cast(type);
            return deduceTypeFromUpperBounds(typeVariable.getBounds());
        }
        throw new DiamondException("The given type["+type+"] is unknown");
    }

    /**
     * Tries to get a raw type from a defined upper bound.<br>
     *     Default to Object for none, or more than one upper bounds
     */
    private static Class<?> deduceTypeFromUpperBounds(Type[] upperBounds) {
        if(upperBounds.length == 1){
            // If there's one, we can use that type as the raw type for the wildcard
            return fromUnspecific(upperBounds[0]);
        }
        // Otherwise object is the safest move
        return Object.class;
    }
}
