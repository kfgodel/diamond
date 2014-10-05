package ar.com.kfgodel.diamond.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.classes.TypeLineage;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.sources.TypeNames;
import ar.com.kfgodel.diamond.api.types.ReferenceOf;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.testobjects.lineage.ChildClass;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type defines specs for test
 * Created by kfgodel on 19/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class ClassInstanceTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a class instance", ()->{

            beforeEach(()->{
                context().classInstance(()-> Diamond.of(ChildClass.class));
            });

            describe("naming", () -> {
                it("has a simple short name", () -> {
                    assertThat(context().classInstance().name())
                            .isEqualTo("ChildClass");
                });

                it("has other names", () -> {
                    TypeNames classNames = context().classInstance().names();
                    assertThat(classNames.classloaderName())
                            .isEqualTo("ar.com.kfgodel.diamond.testobjects.lineage.ChildClass");
                });
            });

            describe("lineage", () -> {
                it("has a lineage with its type ancestors", () -> {
                    TypeLineage typeLineage = context().classInstance().inheritance().typeLineage();
                    assertThat(typeLineage.highestAncestor().name())
                            .isEqualTo("Object");
                });

                /**
                 * This is the runtime parent type
                 */
                it("has a super class", () -> {
                    String superClassName = context().classInstance().inheritance().superclass().map(TypeInstance::name).get();
                    assertThat(superClassName).isEqualTo("ParentClass");
                });

                /**
                 * This is the compile time parent type
                 */
                it("has an extended type", ()->{
                    String extendedTypeName = context().classInstance().inheritance().extendedType().map(Named::name).get();
                    assertThat(extendedTypeName).isEqualTo("ParentClass");
                });
            });

            describe("generics", () -> {
                it("has type parameters", () -> {
                    List<String> parameterNames = context().classInstance().generics().typeParameters()
                            .map((typeParamenter) -> typeParamenter.name())
                            .collect(Collectors.toList());
                    assertThat(parameterNames).isEqualTo(Arrays.asList("C"));
                });

                it("has type arguments", ()-> {
                    List<String> parameterNames = context().classInstance().generics().typeArguments()
                            .map((typeParamenter) -> typeParamenter.name())
                            .collect(Collectors.toList());
                    assertThat(parameterNames).isEqualTo(Arrays.asList());
                });

                /**
                 * The superclass is its un-parameterized supertype (the one that's used on runtime)
                 */
                it("has correct type arguments for its superclass", ()->{
                    List<String> parameterNames = context().classInstance().inheritance().superclass().get().generics().typeArguments()
                            .map((typeParamenter) -> typeParamenter.name())
                            .collect(Collectors.toList());
                    assertThat(parameterNames).isEqualTo(Arrays.asList());
                });

                /**
                 * The extended type is its parameterized supertype (the one that's is used on compile time)
                 */
                it("has correct type arguments for its extended type", ()->{
                    List<String> parameterNames = context().classInstance().inheritance().extendedType().get().generics().typeArguments()
                            .map((typeParamenter) -> typeParamenter.name())
                            .collect(Collectors.toList());
                    assertThat(parameterNames).isEqualTo(Arrays.asList("C", "Integer"));
                });

            });

            describe("for arrays", () -> {
                it("has a component type",()->{
                    context().classInstance(()-> getStringArrayType());
                    assertThat(context().classInstance().componentType().get().name()).isEqualTo("String");
                });
            });


            xit("equality is based on the semantic name", ()->{
                assertThat(Diamond.of(Object.class))
                        .isEqualTo(Diamond.of(Object.class));
            });
            it("contains type parameter values");
            it("contains type argument values");
        });
    }

    private TypeInstance getStringArrayType() {
        return Diamond.types().from(new ReferenceOf<String[]>(){}.getReferencedAnnotatedType());
    }


}
