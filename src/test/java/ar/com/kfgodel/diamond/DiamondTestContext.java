package ar.com.kfgodel.diamond;

import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.classes.ClassLineage;
import ar.com.kfgodel.diamond.api.sources.TypeNames;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.api.LazyValue;

import java.util.function.Supplier;

/**
 * This type defines common test context for diamond specs
 * Created by kfgodel on 17/09/14.
 */
public interface DiamondTestContext extends TestContext {

    ClassLineage lineage();
    void lineage(Supplier<ClassLineage> definition);

    TypeInstance typeInstance();
    void typeInstance(Supplier<TypeInstance> definition);

    LazyValue<Integer> value();
    void value(Supplier<LazyValue<Integer>> definition);

    ClassInstance classInstance();
    void classInstance(Supplier<ClassInstance> definition);

    TypeNames names();
    void names(Supplier<TypeNames> definition);
}
