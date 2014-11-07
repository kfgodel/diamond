package ar.com.kfgodel.diamond.impl.types.packages;

import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

/**
 * This type represents the package of a type
 * Created by kfgodel on 05/11/14.
 */
public class TypePackageImpl implements TypePackage {

    private Supplier<String> name;
    private Supplier<Nary<Annotation>> annotations;

    @Override
    public Nary<Annotation> annotations() {
        return annotations.get();
    }

    @Override
    public String name() {
        return name.get();
    }

    public static TypePackageImpl create(Supplier<String> name, Supplier<Nary<Annotation>> annotations) {
        TypePackageImpl aPackage = new TypePackageImpl();
        aPackage.name = name;
        aPackage.annotations = annotations;
        return aPackage;
    }

}
