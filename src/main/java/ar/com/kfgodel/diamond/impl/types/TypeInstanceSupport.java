package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.annotation.Annotation;
import java.util.Arrays;
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
}
