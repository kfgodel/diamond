package ar.com.kfgodel.functions;

import ar.com.kfgodel.streams.StreamFromCollection;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a function composed of other functions
 * Created by kfgodel on 17/11/14.
 */
public class CompositeFunction implements Function<Object,Object> {

    private Supplier<? extends Stream<Function<Object, Object>>> functions;

    @Override
    public Object apply(Object firstInput) {
        Iterator<Function<Object, Object>> functionIterator = functions.get().iterator();
        Object lastOutput = firstInput;
        while(functionIterator.hasNext()){
            Function<Object, Object> currentFunction = functionIterator.next();
            lastOutput = currentFunction.apply(lastOutput);
        }
        return lastOutput;
    }

    public static CompositeFunction create(Supplier<? extends Stream<Function<Object, Object>>> composedFunctions) {
        CompositeFunction compositeFunction = new CompositeFunction();
        compositeFunction.functions = composedFunctions;
        return compositeFunction;
    }

    public static CompositeFunction create(Collection<Function<Object, Object>> functions) {
        StreamFromCollection<Function<Object, Object>> streamFromCollection = StreamFromCollection.create(functions);
        return create(streamFromCollection);
    }


}

