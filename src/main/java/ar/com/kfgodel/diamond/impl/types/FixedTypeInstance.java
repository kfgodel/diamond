package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.impl.constructors.sources.ImmutableTypeConstructors;
import ar.com.kfgodel.diamond.impl.types.generics.ParameterizedTypeGenerics;
import ar.com.kfgodel.diamond.impl.types.inheritance.SuppliedTypesInheritance;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.Optional;

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

    private LazyValue<Optional<TypeInstance>> componentType;
    private TypeGenerics generics;
    private TypeInheritance inheritance;
    private TypeConstructors constructors;


    @Override
    public Optional<TypeInstance> componentType() {
        return componentType.get();
    }

    @Override
    public TypeGenerics generics() {
        return generics;
    }

    @Override
    public TypeInheritance inheritance() {
        return inheritance;
    }

    @Override
    public TypeConstructors constructors() {
        return constructors;
    }


    /**
     * Creates a class instance with its minimum data
     * @param description the description for this type
     * @return The created instance
     */
    public static FixedTypeInstance create(TypeDescription description) {
        FixedTypeInstance fixedType = new FixedTypeInstance();
        fixedType.initializeSuper(description);
        fixedType.componentType = SuppliedValue.lazilyBy(description.getComponentType());
        fixedType.generics = ParameterizedTypeGenerics.create(description.getTypeParametersSupplier(), description.getTypeArguments());
        fixedType.inheritance = SuppliedTypesInheritance.create(fixedType, description.getSuperclassSupplier(), description.getExtendedTypeSupplier());
        fixedType.constructors = ImmutableTypeConstructors.create(description.getTypeConstructors());
        return fixedType;
    }
}
