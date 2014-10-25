package ar.com.kfgodel.diamond.impl.named;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.naming.NamedSource;
import ar.com.kfgodel.optionals.OptionalFromStream;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type is a base class for sources of named elements, that adds some common source retriever methods
 * Created by kfgodel on 25/10/14.
 */
public abstract class NamedSourceSupport<N extends Named> implements NamedSource<N> {

    protected abstract Stream<N> getAll();
    protected abstract Optional<N> whenExpectingOneAndFoundMore(String elementName, DiamondException e);
    protected abstract N whenExpectingOneAnNoneFound(String elementName);

    @Override
    public Stream<N> named(String elementName) {
        return getAll().filter((element) -> element.name().equals(elementName));
    }

    @Override
    public Optional<N> uniqueNamed(String elementName) throws DiamondException {
        try {
            return OptionalFromStream.using(named(elementName));
        } catch (DiamondException e) {
            return whenExpectingOneAndFoundMore(elementName, e);
        }
    }

    @Override
    public N existingNamed(String methodName) throws DiamondException {
        // NoMethods is the error case
        return uniqueNamed(methodName).orElseGet(()-> whenExpectingOneAnNoneFound(methodName));
    }

}
