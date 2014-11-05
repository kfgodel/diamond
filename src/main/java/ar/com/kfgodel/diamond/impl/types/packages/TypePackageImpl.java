package ar.com.kfgodel.diamond.impl.types.packages;

import ar.com.kfgodel.diamond.api.types.packages.TypePackage;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the package of a type
 * Created by kfgodel on 05/11/14.
 */
public class TypePackageImpl implements TypePackage {

    private Supplier<String> name;
    private Supplier<Stream<Annotation>> annotations;

    @Override
    public Stream<Annotation> annotations() {
        return annotations.get();
    }

    @Override
    public String name() {
        return name.get();
    }

    public static TypePackageImpl create(Supplier<String> name, Supplier<Stream<Annotation>> annotations) {
        TypePackageImpl aPackage = new TypePackageImpl();
        aPackage.name = name;
        aPackage.annotations = annotations;
        return aPackage;
    }

}
