package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.bounds.NoBounds;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type serves as a base implementation for common TypeInstance behavior
 * Created by kfgodel on 20/09/14.
 */
public abstract class TypeInstanceSupport implements TypeInstance {

    /**
     * Constant used to indicate the lack of annotations
     */
    public static final Annotation[] NO_ANNOTATIONS = new Annotation[0];

    private Annotation[] annotations = NO_ANNOTATIONS;

    /**
     * Use this to override default creation with no annotations
     * @param annotations The new annotations
     */
    protected void setAnnotations(Annotation[] annotations) {
        this.annotations = annotations;
    }

    @Override
    public Stream<Annotation> annotations() {
        return Arrays.stream(this.annotations);
    }

    /**
     * Default implementation with no bounds
     * @return A no bounds instance
     */
    @Override
    public TypeBounds bounds() {
        return NoBounds.INSTANCE;
    }

    /**
     * Default implementation with no component type
     * @return An empty optional
     */
    @Override
    public Optional<TypeInstance> componentType() {
        return Optional.empty();
    }

    /**
     * Default implementation with no arguments
     * @return An empty stream
     */
    @Override
    public Stream<TypeInstance> typeArguments() {
        return Stream.empty();
    }
}
