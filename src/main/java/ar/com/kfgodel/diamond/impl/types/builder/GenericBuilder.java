package ar.com.kfgodel.diamond.impl.types.builder;

import ar.com.kfgodel.diamond.impl.types.parts.TypePartsHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents a generic builder that creates a basic state, modifies it through different operations
 * and the it creates something out of it
 * Created by kfgodel on 28/09/14.
 */
public class GenericBuilder<R,S> {

    private Supplier<? extends S> stateCreator;
    private List<Consumer<? super S>> stateModifiers;
    private Function<? super S, ? extends R> resultCreator;

    public R build(){
        S builderState = stateCreator.get();
        for (Consumer<? super S> stateModifier : stateModifiers) {
            stateModifier.accept(builderState);
        }
        R built = resultCreator.apply(builderState);
        return  built;
    }


    public static<R,S> GenericBuilder<R,S> create(Supplier<? extends S> stateCreator) {
        GenericBuilder<R,S> builder = new GenericBuilder<>();
        builder.stateModifiers = new ArrayList<>();
        builder.stateCreator = stateCreator;
        return builder;
    }

    public void addStep(Consumer<? super S> modifierStep){
        this.stateModifiers.add(modifierStep);
    }


    public void removeStep(Consumer<? super TypePartsHelper> modifierStep) {
        this.stateModifiers.remove(modifierStep);
    }
}
