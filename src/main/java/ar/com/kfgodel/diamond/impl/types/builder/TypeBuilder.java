package ar.com.kfgodel.diamond.impl.types.builder;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.parts.TypePartsHelper;

import java.util.function.Consumer;

/**
 *  This type represents a type builder that follows a set of instructions to setup the parts and then build
 * a new type instance
 * Created by kfgodel on 28/09/14.
 */
public class TypeBuilder<T extends TypeInstance> {

    private GenericBuilder<T, TypePartsHelper> internalBuilder;

    private TypeBuilderStep annotationsStep;
    private TypeBuilderStep namesStep;
    private TypeBuilderStep boundsStep;


    public static<T extends TypeInstance> TypeBuilder<T> create() {
        TypeBuilder<T> builder = new TypeBuilder<>();
        builder.internalBuilder = GenericBuilder.create(TypePartsHelper::create);
        //Common to all types
        builder.annotationsStep = TypeBuilderStep.create(builder.internalBuilder);
        builder.namesStep = TypeBuilderStep.create(builder.internalBuilder);
        return builder;
    }

    public void forAnnotations(Consumer<? super TypePartsHelper> step) {
        annotationsStep.defineWith(step);
    }

    public void forNames(Consumer<? super TypePartsHelper> step) {
        namesStep.defineWith(step);
    }

    public void forBounds(Consumer<? super TypePartsHelper> step) {
        if(boundsStep == null){
            // Lazily created because not all types use bounds
            boundsStep = TypeBuilderStep.create(this.internalBuilder);
        }
        boundsStep.defineWith(step);
    }

}
