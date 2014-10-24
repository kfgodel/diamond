package ar.com.kfgodel.diamond.impl.fields.sources;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.fields.TypeFields;
import ar.com.kfgodel.optionals.OptionalFromStream;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the set of fields for a given type stored as immutable
 * Created by kfgodel on 12/10/14.
 */
public class TypeFieldsImpl implements TypeFields {

    private Supplier<Stream<TypeField>> typeFields;

    @Override
    public Stream<TypeField> all() {
        return typeFields.get();
    }

    @Override
    public Stream<TypeField> named(String fieldName) {
        return all().filter((field)-> field.name().equals(fieldName));
    }

    @Override
    public Optional<TypeField> uniqueNamed(String fieldName) throws DiamondException {
        try {
            return OptionalFromStream.create(named(fieldName));
        } catch (Exception e) {
            throw new DiamondException("There's more than one field named \""+fieldName+"\"",e);
        }
    }

    @Override
    public TypeField existingNamed(String fieldName) throws DiamondException {
        // NoFields is the error case
        return uniqueNamed(fieldName).orElseGet(()-> NoFields.INSTANCE.existingNamed(fieldName));
    }

    public static TypeFieldsImpl create(Supplier<Stream<TypeField>> typeFields) {
        TypeFieldsImpl fieldSource = new TypeFieldsImpl();
        fieldSource.typeFields = typeFields;
        return fieldSource;
    }

}
