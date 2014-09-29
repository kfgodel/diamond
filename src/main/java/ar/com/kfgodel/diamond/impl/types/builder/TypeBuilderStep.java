package ar.com.kfgodel.diamond.impl.types.builder;

import ar.com.kfgodel.diamond.impl.types.parts.TypePartsHelper;

import java.util.function.Consumer;

/**
 * This type represents a step to build a type instance that can be overridden with a new step (modifying the build instructions)
 * Created by kfgodel on 28/09/14.
 */
public class TypeBuilderStep {

    private GenericBuilder<?, TypePartsHelper> internalBuilder;
    private Consumer<? super TypePartsHelper> currentStep;

    public void defineWith(Consumer<? super TypePartsHelper> step) {
        if(this.currentStep != null){
            this.internalBuilder.removeStep(this.currentStep);
        }
        this.internalBuilder.addStep(step);
        this.currentStep = step;
    }

    public static TypeBuilderStep create(GenericBuilder<?, TypePartsHelper> internalBuilder) {
        TypeBuilderStep step = new TypeBuilderStep();
        step.internalBuilder = internalBuilder;
        return step;
    }

}
