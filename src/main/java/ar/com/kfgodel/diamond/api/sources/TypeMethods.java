package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.ClassMethod;

import java.util.stream.Stream;

/**
 * This type represents the source of a class method source with the class defined
 * Created by kfgodel on 18/09/14.
 */
public interface TypeMethods {

    /**
     * @return All the class methods of this class
     */
    Stream<ClassMethod> all();
}
