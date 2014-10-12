package ar.com.kfgodel.diamond.api.methods;

import java.util.stream.Stream;

/**
 * This type represents the source of class methods for a type
 * Created by kfgodel on 18/09/14.
 */
public interface TypeMethods {

    /**
     * @return All the class methods for a type
     */
    Stream<ClassMethod> all();
}
