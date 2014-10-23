package ar.com.kfgodel.diamond.impl.fields.sources;

import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.fields.TypeFields;

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

    public static TypeFieldsImpl create(Supplier<Stream<TypeField>> typeFields) {
        TypeFieldsImpl fieldSource = new TypeFieldsImpl();
        fieldSource.typeFields = typeFields;
        return fieldSource;
    }

}
