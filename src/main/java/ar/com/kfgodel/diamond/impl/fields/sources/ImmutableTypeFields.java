package ar.com.kfgodel.diamond.impl.fields.sources;

import ar.com.kfgodel.diamond.api.fields.ClassField;
import ar.com.kfgodel.diamond.api.fields.TypeFields;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the set of fields for a given type stored as immutable
 * Created by kfgodel on 12/10/14.
 */
public class ImmutableTypeFields implements TypeFields {

    private LazyValue<List<ClassField>> typeFields;

    @Override
    public Stream<ClassField> all() {
        return typeFields.get().stream();
    }

    public static ImmutableTypeFields create(Supplier<Stream<ClassField>> typeFields) {
        ImmutableTypeFields fieldSource = new ImmutableTypeFields();
        // Here we cache assuming fields don't change in runtime
        fieldSource.typeFields = SuppliedValue.create(() -> typeFields.get().collect(Collectors.toList()));
        return fieldSource;
    }

}
