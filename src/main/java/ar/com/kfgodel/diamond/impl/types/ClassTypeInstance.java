package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.classes.ClassLineage;
import ar.com.kfgodel.diamond.api.sources.ClassDefinedClassMethodSource;
import ar.com.kfgodel.diamond.impl.classes.NativeClassLineage;
import ar.com.kfgodel.diamond.impl.fragments.SuperClassSupplier;
import ar.com.kfgodel.diamond.impl.sources.ClassDefinedClassMethodSourceImpl;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.lang.annotation.Annotation;
import java.util.Optional;

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

    private String name;
    private String completeName;
    private String declarationName;
    private LazyValue<Optional<ClassInstance>> superclass;

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String completeName() {
        return this.completeName;
    }

    @Override
    public String declarationName() {
        return this.declarationName;
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
            return this.declarationName.equals(((ClassTypeInstance) obj).declarationName);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(completeName);
        return builder.toString();
    }

    public static ClassTypeInstance create(Class<?> nativeClass, Annotation[] annotations) {
        ClassTypeInstance instance = new ClassTypeInstance();
        instance.name = nativeClass.getSimpleName();
        instance.completeName = nativeClass.getName();
        instance.declarationName = instance.completeName;
        instance.superclass = SuppliedValue.create(SuperClassSupplier.create(nativeClass));
        instance.setAnnotations(annotations);
        return instance;
    }

    public static ClassTypeInstance create(Class<?> nativeClass) {
        return create(nativeClass, NO_ANNOTATIONS);
    }

}
