package ar.com.kfgodel.diamond.impl.reflections;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.classes.ClassLineage;
import ar.com.kfgodel.diamond.api.sources.ClassDefinedClassMethodSource;
import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.impl.classes.NativeClassLineage;
import ar.com.kfgodel.diamond.impl.sources.ClassDefinedClassMethodSourceImpl;
import ar.com.kfgodel.diamond.impl.types.TypeInstanceSupport;
import ar.com.kfgodel.diamond.impl.types.bounds.DoubleTypeBounds;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * This type represents a class instance based on a native class instance
 * Created by kfgodel on 18/09/14.
 */
public class NativeClassInstance extends TypeInstanceSupport implements ClassInstance{

    private Class<?> nativeClass;

    @Override
    public String name() {
        return nativeClass.getSimpleName();
    }

    @Override
    public ClassDefinedClassMethodSource methods() {
        return ClassDefinedClassMethodSourceImpl.create(this);
    }

    @Override
    public ClassLineage lineage() {
        return NativeClassLineage.create(this);
    }

    @Override
    public Optional<ClassInstance> getSuperclass() {
        Class<?> superclass = nativeClass.getSuperclass();
        if(superclass == null){
            return Optional.empty();
        }
        return Optional.of(Diamond.of(superclass));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof NativeClassInstance){
            return this.nativeClass.equals(((NativeClassInstance) obj).nativeClass);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(nativeClass.getName());
        return builder.toString();
    }

    public static NativeClassInstance create(Class<?> nativeClass, Annotation[] annotations) {
        NativeClassInstance instance = new NativeClassInstance();
        instance.nativeClass = nativeClass;
        instance.setAnnotations(annotations);
        return instance;
    }

    public static NativeClassInstance create(Class<?> nativeClass) {
        return create(nativeClass, NO_ANNOTATIONS);
    }


    @Override
    public TypeBounds bounds() {
        return DoubleTypeBounds.create(new Type[0], new Type[0]);
    }

}
