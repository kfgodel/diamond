package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.sources.TypeMethods;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Collections;
import java.util.stream.Stream;

/**
 * This type represents the class searcher for methods
 * Created by kfgodel on 19/09/14.
 */
public class TypeMethodSourceImpl implements TypeMethods {

    private TypeInstance typeInstance;

    @Override
    public Stream<ClassMethod> all() {
        return Collections.<ClassMethod>emptyList().stream();
    }

    public static TypeMethodSourceImpl create(TypeInstance typeInstance) {
        TypeMethodSourceImpl methodSource = new TypeMethodSourceImpl();
        methodSource.typeInstance = typeInstance;
        return methodSource;
    }

}
