package ar.com.kfgodel.diamond.api.fields;

import java.util.stream.Stream;

/**
 * This type represents the source of fields for a given type
 * Created by kfgodel on 12/10/14.
 */
public interface TypeFields {

    /**
     * @return All the class fields for a type
     */
    Stream<ClassField> all();

}
