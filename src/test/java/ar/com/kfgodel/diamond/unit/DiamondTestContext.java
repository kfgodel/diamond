package ar.com.kfgodel.diamond.unit;

import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.methods.BoundMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type defines common test context for diamond specs
 * Created by kfgodel on 17/09/14.
 */
public interface DiamondTestContext extends TestContext {

    TypeLineage lineage();
    void lineage(Supplier<TypeLineage> definition);

    TypeInstance typeInstance();
    void typeInstance(Supplier<TypeInstance> definition);

    LazyValue<Integer> value();
    void value(Supplier<LazyValue<Integer>> definition);

    TypeNames names();
    void names(Supplier<TypeNames> definition);

    <T> T object();
    <T> void object(Supplier<T> definition);

    TypeField field();
    void field(Supplier<TypeField> definition);

    TypeMethod method();
    void method(Supplier<TypeMethod> definition);

    TypeConstructor constructor();
    void constructor(Supplier<TypeConstructor> definition);

    String name();
    void name(Supplier<String> definition);

    Class<?> testClass();
    void testClass(Supplier<Class<?>> definition);

    Invokable invokable();
    void invokable(Supplier<Invokable> definition);

    TypePackage typePackage();
    void typePackage(Supplier<TypePackage> definition);

    Nary<Integer> nary();
    void nary(Supplier<Nary<Integer>> definition);

    ExecutableParameter parameter();
    void parameter(Supplier<ExecutableParameter> definition);

    BoundMethod boundMethod();
    void boundMethod(Supplier<BoundMethod> definition);
}
