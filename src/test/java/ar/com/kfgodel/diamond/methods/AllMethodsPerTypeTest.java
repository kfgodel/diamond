package ar.com.kfgodel.diamond.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.testobjects.lineage.ChildClass;
import ar.com.kfgodel.diamond.testobjects.lineage.ParentClass;
import org.junit.runner.RunWith;

import java.lang.reflect.AnnotatedType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests the basic behavior for methods from different types
 * Created by kfgodel on 05/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class AllMethodsPerTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("all methods", () -> {

            describe("for classes", () -> {

                context().typeInstance(() -> Diamond.of(ChildClass.class));

                it("includes public methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((method) -> method.name().equals("aPublicMethod")))
                            .isTrue();
                });

                it("includes protected methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((method)-> method.name().equals("aProtectedMethod")))
                            .isTrue();
                });

                it("includes private methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((method)-> method.name().equals("aPrivateMethod")))
                            .isTrue();
                });

                it("includes default methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((method)-> method.name().equals("aDefaultMethod")))
                            .isTrue();
                });

                it("includes static methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((method)-> method.name().equals("aStaticMethod")))
                            .isTrue();
                });

                it("includes inherited methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((method)-> method.name().equals("aParentMethod")))
                            .isTrue();
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((method)-> method.name().equals("toString")))
                            .isTrue();
                });

            });

            describe("for type variables and wildcards", () -> {

                describe("without upper bounds", () -> {

                    context().typeInstance(AllMethodsPerTypeTest::getUnboundedWildcardType);

                    it("includes all the methods from Object", () -> {
                        assertThat(context().typeInstance().methods().all()
                                .anyMatch((method) -> method.name().equals("equals")))
                                .isTrue();
                        assertThat(context().typeInstance().methods().all()
                                .anyMatch((method) -> method.name().equals("notify")))
                                .isTrue();
                    });
                });

                describe("with 1 upper bound", () -> {

                    context().typeInstance(AllMethodsPerTypeTest::getChildClassSubTypeWildcardType);

                    it("includes methods from the upper type",()->{
                        assertThat(context().typeInstance().methods().all()
                                .anyMatch((method) -> method.name().equals("aPublicMethod")))
                                .isTrue();
                    });

                    it("includes inherited methods from the upper type hierarchy",()->{
                        assertThat(context().typeInstance().methods().all()
                                .anyMatch((method) -> method.name().equals("aParentMethod")))
                                .isTrue();
                    });
                });

                describe("with more than 1 upper bound", () -> {

                    context().typeInstance(AllMethodsPerTypeTest::getComparableAndNumberSubtypeVariableType);

                    it("includes methods from all the bounds",()->{
                        assertThat(context().typeInstance().methods().all()
                                .anyMatch((method) -> method.name().equals("compareTo")))
                                .isTrue();
                        assertThat(context().typeInstance().methods().all()
                                .anyMatch((method) -> method.name().equals("addAll")))
                                .isTrue();

                    });

                });

            });


            describe("for array types", () -> {
                context().typeInstance(() -> Diamond.of(String[].class));

                it("includes all methods from Object",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((method) -> method.name().equals("wait")))
                            .isTrue();
                });

            });


            describe("for primitive types", () -> {
                context().typeInstance(() -> Diamond.of(int.class));

                it("is empty",()->{
                    assertThat(context().typeInstance().methods().all().count())
                            .isEqualTo(0);
                });

            });

            describe("for parameterized types", ()->{
                context().typeInstance(AllMethodsPerTypeTest::getParameterizedParentClass);

                it("includes the same methods as the raw class",()->{
                    assertThat(context().typeInstance().methods().all().collect(Collectors.toList()))
                            .isEqualTo(Diamond.of(ParentClass.class).methods().all().collect(Collectors.toList()));
                });
            });

        });

    }


    private static TypeInstance getUnboundedWildcardType(){
        TypeInstance listType = getTypeFrom(new ReferenceOf<List<?>>() {});
        TypeInstance unboundedWildcard = listType.generics().typeArguments().findFirst().get();
        return unboundedWildcard;
    }

    private static TypeInstance getChildClassSubTypeWildcardType(){
        return getTypeFrom(new ReferenceOf<List<? extends ChildClass>>() {}).generics().typeArguments().findFirst().get();
    }

    private static <A extends Comparable & Collection> TypeInstance getComparableAndNumberSubtypeVariableType(){
        return getTypeFrom(new ReferenceOf<A>() {});
    }

    private static TypeInstance getTypeFrom(ReferenceOf<?> reference) {
        AnnotatedType annotatedType = reference.getReferencedAnnotatedType();
        TypeInstance typeInstance = Diamond.types().from(annotatedType);
        return typeInstance;
    }

    private static TypeInstance getParameterizedParentClass() {
        return getTypeFrom(new ReferenceOf<ParentClass<String, Integer>>() {});
    }

}