package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.classes.ClassLineage;
import ar.com.kfgodel.diamond.api.sources.ClassDefinedClassMethodSource;
import ar.com.kfgodel.diamond.api.sources.ClassDefinedClassNameSource;
import ar.com.kfgodel.diamond.impl.classes.NativeClassLineage;
import ar.com.kfgodel.diamond.impl.fragments.SuperClassSupplier;
import ar.com.kfgodel.diamond.impl.naming.ClassNames;
import ar.com.kfgodel.diamond.impl.sources.ClassDefinedClassMethodSourceImpl;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents a class instance based on a native class instance.<br>
 * <br>
 * Implementation notes:
 * - Due to class being an access point for different uses and data, most of their attributes are implemented lazy
 *   so their don't explode the entire possible tree. LazyValue variables allow ignore aspects of a class until needed
 *
 * Created by kfgodel on 18/09/14.
 */
public class ClassTypeInstance extends TypeInstanceSupport implements ClassInstance{

    private ClassDefinedClassNameSource names;
    private LazyValue<Optional<ClassInstance>> superclass;

    @Override
    public String name() {
        return this.names.shortName();
    }

    @Override
    public ClassDefinedClassNameSource names() {
        return this.names;
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
        return superclass.get();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ClassTypeInstance){
            return this.names().declarationName().equals(((ClassTypeInstance) obj).names().declarationName());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.names().classloaderName());
        return builder.toString();
    }


    /**
     * Creates a class instance with its minimum data
     * @param names The names of the class
     * @param superclassSupplier The superclass provider
     * @param annotations The attached annotations
     * @return The created instance
     */
    public static ClassTypeInstance create(ClassDefinedClassNameSource names, Supplier<Optional<ClassInstance>> superclassSupplier, Annotation[] annotations) {
        ClassTypeInstance classInstance = new ClassTypeInstance();
        classInstance.names = names;
        classInstance.superclass = SuppliedValue.create(superclassSupplier);
        classInstance.setAnnotations(annotations);
        return classInstance;
    }


    public static ClassTypeInstance create(Class<?> nativeClass, Annotation[] annotations) {
        return create(ClassNames.create(nativeClass), SuperClassSupplier.create(nativeClass), annotations);
    }

    public static ClassTypeInstance create(Class<?> nativeClass) {
        return create(nativeClass, NO_ANNOTATIONS);
    }

}
