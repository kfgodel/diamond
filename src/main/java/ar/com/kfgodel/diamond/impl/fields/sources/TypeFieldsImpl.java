package ar.com.kfgodel.diamond.impl.fields.sources;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.fields.TypeFields;
import ar.com.kfgodel.diamond.impl.named.NamedSourceSupport;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the set of fields for a given type stored as immutable
 * Created by kfgodel on 12/10/14.
 */
public class TypeFieldsImpl extends NamedSourceSupport<TypeField> implements TypeFields {

    private Supplier<Stream<TypeField>> typeFields;

    @Override
    public Stream<TypeField> all() {
        return typeFields.get();
    }

    @Override
    protected Stream<TypeField> getAll() {
        return all();
    }

    @Override
    protected Optional<TypeField> whenExpectingOneAndFoundMore(String fieldName, DiamondException e) {
        throw new DiamondException("There's more than one field named \""+fieldName+"\"",e);
    }

    @Override
    protected TypeField whenExpectingOneAnNoneFound(String fieldName) {
        return NoFields.INSTANCE.existingNamed(fieldName);
    }

    public static TypeFieldsImpl create(Supplier<Stream<TypeField>> typeFields) {
        TypeFieldsImpl fieldSource = new TypeFieldsImpl();
        fieldSource.typeFields = typeFields;
        return fieldSource;
    }

}
