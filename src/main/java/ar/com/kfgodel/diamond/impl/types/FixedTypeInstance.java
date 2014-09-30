package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.classes.TypeLineage;
import ar.com.kfgodel.diamond.api.sources.TypeMethodSource;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.classes.ExtendedTypeLineage;
import ar.com.kfgodel.diamond.impl.sources.TypeMethodSourceImpl;
import ar.com.kfgodel.diamond.impl.types.description.TypeDescription;
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
 * - Due to class being an access point for many different use cases and data, most of their attributes are implemented lazy
 *   so their don't prematurely explode the entire possible tree. LazyValue variables allow ignoring aspects of a class until needed
 *
 * Created by kfgodel on 18/09/14.
 */
public class FixedTypeInstance extends TypeInstanceSupport {

    private LazyValue<Optional<TypeInstance>> superclass;
    private LazyValue<Optional<TypeInstance>> extendedType;
    private LazyValue<Optional<TypeInstance>> componentType;
    private LazyValue<List<TypeInstance>> typeArguments;
    private LazyValue<List<TypeInstance>> typeParameters;

    @Override
    public TypeMethodSource methods() {
        return TypeMethodSourceImpl.create(this);
    }

    @Override
    public TypeLineage lineage() {
        return ExtendedTypeLineage.create(this);
    }

    @Override
    public Stream<TypeInstance> typeArguments() {
        return typeArguments.get().stream();
    }

    @Override
    public Stream<TypeInstance> typeParameters() {
        return this.typeParameters.get().stream();
    }

    @Override
    public Optional<TypeInstance> componentType() {
        return componentType.get();
    }

    @Override
    public Optional<TypeInstance> superclass() {
        return superclass.get();
    }

    @Override
    public Optional<TypeInstance> extendedType() {
        return this.extendedType.get();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FixedTypeInstance){
            return this.names().declarationName().equals(((FixedTypeInstance) obj).names().declarationName());
        }
        return false;
    }

    /**
     * Creates a class instance with its minimum data
     * @param description the description for this type
     * @return The created instance
     */
    public static FixedTypeInstance create(TypeDescription description) {
        FixedTypeInstance fixedType = new FixedTypeInstance();
        fixedType.setNames(description.getNames());
        fixedType.setAnnotations(description.getAnnotations());
        fixedType.superclass = SuppliedValue.create(description.getSuperclassSupplier());
        fixedType.extendedType = SuppliedValue.create(description.getExtendedTypeSupplier());
        fixedType.typeParameters = SuppliedValue.create(description.getTypeParametersSupplier());
        fixedType.typeArguments = SuppliedValue.create(description.getTypeArguments());
        fixedType.componentType = SuppliedValue.create(description.getComponentType());
        return fixedType;
    }

}
