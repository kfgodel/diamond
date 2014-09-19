package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.ClassDefinedClassMethodSource;
import ar.com.kfgodel.diamond.impl.methods.NativeSignature;

import java.util.Collections;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This type represents the class searcher for methods
 * Created by kfgodel on 19/09/14.
 */
public class ClassDefinedClassMethodSourceImpl implements ClassDefinedClassMethodSource {

    private ClassInstance classInstance;

    @Override
    public ClassMethod identifiedAs(String methodName, Class<?>... parameterTypes) {
        NativeSignature expectedSignature = NativeSignature.create(methodName, parameterTypes);
        Stream<ClassMethod> methods = classInstance.methods().all()
                .filter((classMethod) -> classMethod.signature().equals(expectedSignature) );
        Optional<ClassMethod> onlyMethod = methods.findFirst();
        return onlyMethod.orElseThrow(() -> new DiamondException("Method with signature["+expectedSignature+"] couldn't be found on class["+classInstance+"]"));
    }

    @Override
    public Stream<ClassMethod> all() {
        return Collections.<ClassMethod>emptyList().stream();
    }

    public static ClassDefinedClassMethodSourceImpl create(ClassInstance classInstance) {
        ClassDefinedClassMethodSourceImpl methodSource = new ClassDefinedClassMethodSourceImpl();
        methodSource.classInstance = classInstance;
        return methodSource;
    }

}
