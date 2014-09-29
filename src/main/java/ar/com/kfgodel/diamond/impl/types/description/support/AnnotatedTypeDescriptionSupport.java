package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.impl.types.description.TypeDescription;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.AnnotatedTypeAnnotationsSupplier;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.function.Supplier;

/**
 * This type serves as base for annotated types description that are extension of other descriptions
 * Created by kfgodel on 28/09/14.
 */
public abstract class AnnotatedTypeDescriptionSupport extends DelegatedDescriptionSupport {

    private TypeDescription unannotatedDescription;

    @Override
    protected TypeDescription getDelegateDescription() {
        if (unannotatedDescription == null) {
            unannotatedDescription = createUnannotatedDescription();
        }
        return unannotatedDescription;
    }

    @Override
    public Supplier<Annotation[]> getAnnotations() {
        return AnnotatedTypeAnnotationsSupplier.create(getAnnotatedType());
    }

    protected abstract AnnotatedType getAnnotatedType();

    protected abstract TypeDescription createUnannotatedDescription();

}
