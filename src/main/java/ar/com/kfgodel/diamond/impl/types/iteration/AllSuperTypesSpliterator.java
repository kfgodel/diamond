package ar.com.kfgodel.diamond.impl.types.iteration;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.*;
import java.util.function.Consumer;

/**
 * This type represents the spliterator that traverses all the supertypes of a given type, avoiding duplicates
 * Created by kfgodel on 16/11/14.
 */
public class AllSuperTypesSpliterator implements Spliterator<TypeInstance> {

    private Set<TypeInstance> visitedTypes;
    private Queue<TypeInstance> pendingTypes;

    @Override
    public boolean tryAdvance(Consumer<? super TypeInstance> action) {
        if(pendingTypes.isEmpty()){
            // No more elements
            return false;
        }
        TypeInstance currentType = pendingTypes.poll();
        visitedTypes.add(currentType);
        addNonVisitedSuperTypesAsPending(currentType);
        action.accept(currentType);
        return true;
    }

    private void addNonVisitedSuperTypesAsPending(TypeInstance currentType) {
        currentType.inheritance().supertypes()
                .filter((superType)-> !visitedTypes.contains(superType))
                .filter((nonVisited) -> !pendingTypes.contains(nonVisited))
                .forEach((notAlreadyPending) -> pendingTypes.add(notAlreadyPending));
    }

    @Override
    public Spliterator<TypeInstance> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE;
    }

    @Override
    public int characteristics() {
        return Spliterator.DISTINCT & Spliterator.IMMUTABLE & Spliterator.NONNULL & Spliterator.ORDERED;
    }


    public static AllSuperTypesSpliterator create(TypeInstance startingType) {
        AllSuperTypesSpliterator spliterator = new AllSuperTypesSpliterator();
        spliterator.pendingTypes  = new LinkedList<>();
        spliterator.visitedTypes = new HashSet<>();
        spliterator.pendingTypes.add(startingType);
        return spliterator;
    }

}
