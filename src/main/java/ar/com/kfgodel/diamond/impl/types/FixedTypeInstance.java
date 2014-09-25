package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.classes.ClassLineage;
import ar.com.kfgodel.diamond.api.sources.ClassDefinedClassMethodSource;
import ar.com.kfgodel.diamond.api.sources.ClassDefinedClassNameSource;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.classes.NativeClassLineage;
import ar.com.kfgodel.diamond.impl.sources.ClassDefinedClassMethodSourceImpl;
import ar.com.kfgodel.diamond.impl.types.parts.TypeParts;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents a concrete type that can be instantiated (class, parameterized type, array), excluding
 * variable types such as type variables or wildcards.<br>
 * <br>
 * Implementation notes:
 * - Due to class being an access point for different uses and data, most of their attributes are implemented lazy
 *   so their don't explode the entire possible tree. LazyValue variables allow ignore aspects of a class until needed
 *
 * Created by kfgodel on 18/09/14.
 */
public class FixedTypeInstance extends TypeInstanceSupport implements ClassInstance{

    private ClassDefinedClassNameSource names;
    private LazyValue<Optional<ClassInstance>> superclass;
    private List<TypeInstance> typeArguments;
    private Optional<TypeInstance> componentType;

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
    public Stream<TypeInstance> typeArguments() {
        return typeArguments.stream();
    }

    @Override
    public Optional<TypeInstance> componentType() {
        return componentType;
    }

    @Override
    public Optional<ClassInstance> getSuperclass() {
        return superclass.get();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FixedTypeInstance){
            return this.names().declarationName().equals(((FixedTypeInstance) obj).names().declarationName());
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
     * @param parts the parts needed to create the instance
     * @return The created instance
     */
    public static FixedTypeInstance create(TypeParts parts) {
        FixedTypeInstance classInstance = new FixedTypeInstance();
        classInstance.names = parts.getNames();
        classInstance.superclass = SuppliedValue.create(parts.getSuperclassSupplier());
        classInstance.typeArguments = parts.getTypeArguments();
        classInstance.componentType = parts.getComponentType();
        classInstance.setAnnotations(parts.getAnnotations());
        return classInstance;
    }

}
