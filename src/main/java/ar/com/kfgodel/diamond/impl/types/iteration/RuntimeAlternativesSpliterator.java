package ar.com.kfgodel.diamond.impl.types.iteration;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * This type represents the iterator of types with their runtime counterparts
 * *
 * Created by kfgodel on 13/02/15.
 */
public class RuntimeAlternativesSpliterator implements Spliterator<TypeInstance> {
    
    private Iterator<TypeInstance> supplierIterator;
    private Optional<TypeInstance> lastRuntimeAlternative;
    
    @Override
    public boolean tryAdvance(Consumer<? super TypeInstance> action) {
        if(!lastRuntimeAlternative.isPresent() && !supplierIterator.hasNext()){
            return false;
        }
        TypeInstance nextType;
        if (lastRuntimeAlternative.isPresent()) {
            nextType = lastRuntimeAlternative.get();
            lastRuntimeAlternative = Optional.empty();
        } else {
            nextType = supplierIterator.next();
            TypeInstance runtimeVersion = nextType.generics().runtimeType();
            if (runtimeVersion.equals(nextType)) {
                //No need to iterate it
                lastRuntimeAlternative = Optional.empty();
            } else {
                lastRuntimeAlternative = Optional.of(runtimeVersion);
            }
        }
        action.accept(nextType);
        return true;
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

    public static RuntimeAlternativesSpliterator create(Iterator<TypeInstance> baseIterator) {
        RuntimeAlternativesSpliterator spliterator = new RuntimeAlternativesSpliterator();
        spliterator.supplierIterator = baseIterator;
        spliterator.lastRuntimeAlternative = Optional.empty();
        return spliterator;
    }

}
