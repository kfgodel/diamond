package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.TypeSources;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.FixedTypeInstance;
import ar.com.kfgodel.diamond.impl.types.VariableTypeInstance;
import ar.com.kfgodel.diamond.impl.types.description.TypeDescriptor;

/**
 * This type implements the spurces for type instances
 * Created by kfgodel on 20/09/14.
 */
public class TypeSourceImpl implements TypeSources {

    @Override
    public TypeInstance fromDescription(TypeDescription description) {
        // If we plan to cache values this should be the place
        return createTypeFrom(description);
    }

    /**
     * Creates a new type instance from its description
     * @param description The description of the type traits
     * @return The created type instance
     */
    private TypeInstance createTypeFrom(TypeDescription description) {
        if(description.isForVariableType()){
            return VariableTypeInstance.create(description);
        }
        return FixedTypeInstance.create(description);
    }


    @Override
    public TypeInstance from(Object type) throws DiamondException {
        TypeDescription typeDescription = TypeDescriptor.INSTANCE.describe(type);
        return fromDescription(typeDescription);
    }

    public static TypeSourceImpl create() {
        TypeSourceImpl source = new TypeSourceImpl();
        return source;
    }

}
