package ar.com.kfgodel.diamond.impl.fragments;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a fragment of code that can supply the actual arguments for a type based on the parameterization of
 * its subtype.<br>
 * Given that the parameters of the subtype become the arguments of the supertype. The actual arguments of a supertype
 * can be calculated from the parameters of the subtype used to as arguments, that have a subtype argument replacement
 * Created by kfgodel on 26/09/14.
 */
public class SuperTypeArgsMatcher<T> implements Supplier<Stream<T>> {

    private Stream<T> supertypeArguments;
    private Stream<T> subtypeParameters;
    private Stream<T> subtypeArguments;
    private Map<T, T> subtypeArgumentsPerParamenter;

    @Override
    public Stream<T> get() {
        relateSubtypeArgumentsAndParameters();
        return supertypeArguments.map(this::replaceWithSubtypeArguments);
    }

    /**
     * Creates the mapping between subtype parameters and subtype arguments, that allows later
     * resolution of supertype arguments from subtype parameters
     */
    private void relateSubtypeArgumentsAndParameters() {
        this.subtypeArgumentsPerParamenter = new HashMap<>();
        Iterator<T> argumentIterator = subtypeArguments.iterator();
        Iterator<T> parameterIterator = subtypeParameters.iterator();
        while(argumentIterator.hasNext() && parameterIterator.hasNext()){
            T parameter = parameterIterator.next();
            T argument = argumentIterator.next();
            subtypeArgumentsPerParamenter.put(parameter, argument);
        }
    }

    /**
     * Tries to get the actual argument used on subtype for the given type variable
     * @param superTypeArgument The variable used to parameterize the super type
     * @return The actual argument defined in subtype for that variable, or the same variable if none found
     */
    private T replaceWithSubtypeArguments(T superTypeArgument) {
        T subTypeArgument = subtypeArgumentsPerParamenter.get(superTypeArgument);
        if (subTypeArgument != null) {
            return subTypeArgument;
        }
        // There's no argument defined in subtype for that variable
        return superTypeArgument;
    }

    public static<T> SuperTypeArgsMatcher<T> create(Stream<T> subtypeArguments, Stream<T> subtypeParameters, Stream<T> supertypeArguments) {
        SuperTypeArgsMatcher supplier = new SuperTypeArgsMatcher();
        supplier.subtypeArguments = subtypeArguments;
        supplier.subtypeParameters = subtypeParameters;
        supplier.supertypeArguments = supertypeArguments;
        return supplier;
    }

}
