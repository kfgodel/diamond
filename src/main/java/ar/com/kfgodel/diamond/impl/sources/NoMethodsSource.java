package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.sources.TypeMethodSource;

import java.util.stream.Stream;

/**
 * This type represents a source for a type that has no methods
 * Created by kfgodel on 30/09/14.
 */
public class NoMethodsSource implements TypeMethodSource {

    public static final NoMethodsSource INSTANCE = new NoMethodsSource();

    @Override
    public ClassMethod identifiedAs(String methodName, Class<?>... parameterTypes) {
        return null;
    }

    @Override
    public Stream<ClassMethod> all() {
        return null;
    }

}
