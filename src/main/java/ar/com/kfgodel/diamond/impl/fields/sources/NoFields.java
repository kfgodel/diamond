package ar.com.kfgodel.diamond.impl.fields.sources;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.fields.TypeFields;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents the fields of a type that has no fields
 * Created by kfgodel on 12/10/14.
 */
public class NoFields implements TypeFields {

    public static final NoFields INSTANCE = new NoFields();

    @Override
    public Stream<TypeField> all() {
        return Stream.empty();
    }

    @Override
    public Stream<TypeField> named(String fieldName) {
        return Stream.empty();
    }

    @Override
    public Optional<TypeField> uniqueNamed(String fieldName) throws DiamondException {
        return Optional.empty();
    }

    @Override
    public TypeField existingNamed(String fieldName) throws DiamondException {
        throw new DiamondException("There's no field named \""+fieldName+"\"");
    }
}
