package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.sources.TypeMethods;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.sources.TypeMethodSourceImpl;
import ar.com.kfgodel.diamond.impl.types.description.TypeDescription;
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
    private ParameterizedTypeGenerics generics;
    private TypeInheritance inheritance;


    @Override
    public TypeMethods methods() {
        return TypeMethodSourceImpl.create(this);
    }

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

    /**
     * Creates a class instance with its minimum data
     * @param description the description for this type
     * @return The created instance
     */
    public static FixedTypeInstance create(TypeDescription description) {
        FixedTypeInstance fixedType = new FixedTypeInstance();
        fixedType.setNames(description.getNames());
        fixedType.setAnnotations(description.getAnnotations());
        fixedType.componentType = SuppliedValue.create(description.getComponentType());
        fixedType.generics = ParameterizedTypeGenerics.create(description.getTypeParametersSupplier(), description.getTypeArguments());
        fixedType.inheritance = SuppliedTypesInheritance.create(fixedType, description.getSuperclassSupplier(), description.getExtendedTypeSupplier());
        return fixedType;
    }

}
