package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.TypeMethodSource;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.methods.NativeSignature;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents the class searcher for methods
 * Created by kfgodel on 19/09/14.
 */
public class TypeMethodSourceImpl implements TypeMethodSource {

    private TypeInstance typeInstance;

    @Override
    public ClassMethod identifiedAs(String methodName, Class<?>... parameterTypes) {
        NativeSignature expectedSignature = NativeSignature.create(methodName, parameterTypes);
        Stream<ClassMethod> methods = typeInstance.methods().all()
                .filter((classMethod) -> classMethod.signature().equals(expectedSignature) );
        Optional<ClassMethod> onlyMethod = methods.findFirst();
        return onlyMethod.orElseThrow(() -> new DiamondException("Method with signature["+expectedSignature+"] couldn't be found on class["+ typeInstance +"]"));
    }

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
