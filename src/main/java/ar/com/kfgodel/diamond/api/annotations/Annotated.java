package ar.com.kfgodel.diamond.api.annotations;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

/**
 * This type represents an object that carries annotations with it
 * Created by kfgodel on 20/09/14.
 */
public interface Annotated {

    /**
     * @return The set of annotations represents in this object
     */
    Stream<Annotation> annotations();
}
