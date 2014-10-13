package ar.com.kfgodel.diamond.api.types.reference;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.lang.reflect.*;

/**
 * This type represents a reference that can be used to obtain a parameterized types.<br>
 * By using a parameterized subclass of this type you can reference complex generic types.<br>
 * <br>
 * Example usage: new TypeReference&lt;ArrayList&lt;String&gt;&gt;(){}.type();<br>
 * <br>
 * Taken from: http://gafter.blogspot.com.ar/2006/12/super-type-tokens.html<br>
 * @author crazybob@google.com (Bob Lee)
 */
public abstract class ReferenceOf<T> {

    private final Type referencedType;

    private final AnnotatedType referencedAnnotatedType;

    public ReferenceOf() {
        Class<?> concreteSuperClass = getClass().getSuperclass();
        if(!concreteSuperClass.equals(ReferenceOf.class)){
            throw new DiamondException(ReferenceOf.class.getSimpleName() + " should have a direct subclass (no multi-class hierarchy supported");
        }
        AnnotatedType annotatedSuperclass = getClass().getAnnotatedSuperclass();
        if (!(annotatedSuperclass instanceof AnnotatedParameterizedType)) {
            throw new DiamondException(ReferenceOf.class.getSimpleName() + " must be parameterized with a generic type to be used");
        }
        AnnotatedParameterizedType parameterizedReferenceType = (AnnotatedParameterizedType) annotatedSuperclass;
        this.referencedAnnotatedType = parameterizedReferenceType.getAnnotatedActualTypeArguments()[0];
        this.referencedType = this.referencedAnnotatedType.getType();
    }

    /**
     * Gets the referenced type.
     */
    public Type getReferencedType() {
        return this.referencedType;
    }

    /**
     * Returns the referenced type as a parameterized type, failing with an exception if the referenced type is not parameterized
     * @return The parameterized type
     */
    public ParameterizedType getReferencedParameterizedType() {
        try {
            return (ParameterizedType) referencedType;
        } catch (ClassCastException e) {
            throw new DiamondException("Referenced type["+this.referencedType+"] is not a parameterized type");
        }
    }

    /**
     * @param <T> Type of expected class instance
     * @return The referenced type casted as an instance of Class class
     */
    public<T> Class<T> getReferencedClass() {
        try {
            return (Class<T>) referencedType;
        } catch (ClassCastException e) {
            throw new DiamondException("Referenced type["+this.referencedType+"] is not a Class instance");
        }
    }

    /**
     * @return The referenced type variable
     */
    public TypeVariable getReferencedTypeVariable() {
        try {
            return (TypeVariable) referencedType;
        } catch (ClassCastException e) {
            throw new DiamondException("Referenced type["+this.referencedType+"] is not a Type Variable");
        }
    }

    /**
     * @return The referenced type wildcard
     */
    public WildcardType getReferencedWildcard(){
        try {
            return (WildcardType) referencedType;
        } catch (ClassCastException e) {
            throw new DiamondException("Referenced type["+this.referencedType+"] is not a type wildcard");
        }
    }

    /**
     * @return The referenced array type
     */
    public GenericArrayType getGenericArrayType(){
        try {
            return (GenericArrayType) referencedType;
        } catch (ClassCastException e) {
            throw new DiamondException("Referenced type["+this.referencedType+"] is not a generic array type");
        }
    }


    /**
     * @return True if referenced type is a Class instance
     */
    public boolean referencesAClass(){
        return referencedType instanceof Class;
    };

    /**
     * @return True if referenced type is a parameterized type
     */
    public boolean referencesAParameterizedType() {
        return referencedType instanceof ParameterizedType;
    }

    /**
     * @return true if referenced type is an instance of TypeVariable
     */
    public boolean referencesATypeVariable(){
        return referencedType instanceof TypeVariable;
    }

    /**
     * @return true if referenced type is an instance of WildcardType
     */
    public boolean referencesAWildCard(){
        return referencedType instanceof WildcardType;
    }

    /**
     * @return true if referenced type is instance of GenericArrayType
     */
    public boolean referencesAGenericArrayType(){
        return referencedType instanceof GenericArrayType;
    }

    /**
     * @return Returns the annotated type that contains referenced type and its annotations
     */
    public AnnotatedType getReferencedAnnotatedType() {
        return referencedAnnotatedType;
    }

}