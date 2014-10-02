package ar.com.kfgodel.diamond.impl.types.description.extended;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.description.TypeDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.DelegatedDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.typearguments.ExtendedTypeArgumentsSupplier;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the description of an extended type (one that is parameterized by another one)
 * Created by kfgodel on 29/09/14.
 */
public class ExtendedTypeDescription extends DelegatedDescriptionSupport {

    private TypeDescription extendedTypeDescription;
    private Consumer<List<TypeInstance>> extendedTypeArgumentsReplacer;

    @Override
    protected TypeDescription getDelegateDescription() {
        return extendedTypeDescription;
    }

    @Override
    public Supplier<Stream<TypeInstance>> getTypeArguments() {
        return ExtendedTypeArgumentsSupplier.create(extendedTypeDescription.getTypeArguments(), extendedTypeArgumentsReplacer);
    }

    public static ExtendedTypeDescription create(TypeDescription otherTypeDescription, Consumer<List<TypeInstance>> extendedTypeArgumentsReplacer) {
        ExtendedTypeDescription description = new ExtendedTypeDescription();
        description.extendedTypeDescription = otherTypeDescription;
        description.extendedTypeArgumentsReplacer = extendedTypeArgumentsReplacer;
        return description;
    }

}
