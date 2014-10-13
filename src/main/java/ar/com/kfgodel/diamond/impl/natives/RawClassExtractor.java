package ar.com.kfgodel.diamond.impl.natives;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.lang.reflect.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This type knows how to extract the raw class from its generified version.<br>
 *     This fragment is specially needed for generic arrays, and is taken from: http://www.java2s.com/Code/Java/Reflection/GetRawClass.htm
 * Created by kfgodel on 21/09/14.
 */
public class RawClassExtractor {

    /**
     * Extracts the raw type from the given parameterized type.<br>
     *  It's raw type is accessible from it directly but we cannot ascertain that it's a class
     * @param parameterizedType The parameterized type
     * @return The class instance for that type
     */
    private static Set<Class<?>> from(ParameterizedType parameterizedType){
        Type unparameterizedType = parameterizedType.getRawType();
        return fromUnspecific(unparameterizedType);
    }

    /**
     * Extracts the raw type from the given generic array type.<br>
     *  This method creates a new array instance to get the actual raw class from the VM.<br>
     *  There's no other way to get an array class than from an instance
     * @param genericArrayType The generic type
     * @return The class instance
     */
    private static Class<?> from(GenericArrayType genericArrayType){
        Type arrayComponentType = genericArrayType.getGenericComponentType();
        Set<Class<?>> rawComponentClasses = fromUnspecific(arrayComponentType);
        Class<?> rawComponentClass = coalesce(rawComponentClasses);
        return Array.newInstance(rawComponentClass, 0).getClass();
    }

    /**
     * Simplifies the multiple types problem into one, choosing the only type in the list or Object
     * @param rawComponentClasses
     * @return
     */
    public static Class<?> coalesce(Set<Class<?>> rawComponentClasses) {
        if(rawComponentClasses.size() == 1){
            return rawComponentClasses.stream().findFirst().get();
        }
        // The common super type for all other types
        return Object.class;
    }

    /**
     * Generic type extractor method for unspecific Type instances.<br>
     * For bound types this will return the upper bounds list, or Object if no bound.<br>
     * This method may be called recursively from generified types.<br>
     * @param type The instance to degenerify
     * @return The list of raw classes
     */
    public static Set<Class<?>> fromUnspecific(final Type type) {
        if (Class.class.isInstance(type)) {
            return createSetFor(Class.class.cast(type));
        }
        if (ParameterizedType.class.isInstance(type)) {
            return from(ParameterizedType.class.cast(type));
        }
        if (GenericArrayType.class.isInstance(type)) {
            return createSetFor(from(GenericArrayType.class.cast(type)));
        }
        if(WildcardType.class.isInstance(type)){
            WildcardType wildcardType = WildcardType.class.cast(type);
            return deduceTypesFromUpperBounds(wildcardType.getUpperBounds());
        }
        if(TypeVariable.class.isInstance(type)){
            TypeVariable typeVariable = TypeVariable.class.cast(type);
            return deduceTypesFromUpperBounds(typeVariable.getBounds());
        }
        throw new DiamondException("The given type["+type+"] is unknown");
    }

    private static Set<Class<?>> createSetFor(Class<?> nativeClass) {
        Set<Class<?>> list = new LinkedHashSet<>(1);
        list.add(nativeClass);
        return list;
    }

    /**
     * Tries to get a raw type from a defined upper bound.<br>
     *     Default to Object for none, or more than one upper bounds
     */
    private static Set<Class<?>> deduceTypesFromUpperBounds(Type[] upperBounds) {
        if(upperBounds.length == 0){
            // Unbounded means that we only know that's an Object
            return createSetFor(Object.class);
        }
        Set<Class<?>> rawUpperBounds = new LinkedHashSet<>(upperBounds.length);
        for (Type upperBound : upperBounds) {
            Set<Class<?>> expandedUpperBound = fromUnspecific(upperBound);
            rawUpperBounds.addAll(expandedUpperBound);
        }
        return rawUpperBounds;
    }
}
