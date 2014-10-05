package ar.com.kfgodel.diamond.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.ReferenceOf;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.testobjects.lineage.ChildClass;
import org.junit.runner.RunWith;

import java.lang.reflect.AnnotatedType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests the basic behavior for methods from different types
 * Created by kfgodel on 05/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class MethodsPerTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("all methods", () -> {

            describe("for classes", () -> {

                context().typeInstance(() -> Diamond.of(ChildClass.class));

                it("includes public methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((methods) -> methods.name().equals("aPublicMethod")))
                            .isTrue();
                });

                it("includes protected methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((methods)-> methods.name().equals("aProtectedMethod")))
                            .isTrue();
                });

                it("includes private methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((methods)-> methods.name().equals("aPrivateMethod")))
                            .isTrue();
                });

                it("includes default methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((methods)-> methods.name().equals("aDefaultMethod")))
                            .isTrue();
                });

                it("includes static methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((methods)-> methods.name().equals("aStaticMethod")))
                            .isTrue();
                });

                it("includes inherited methods",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((methods)-> methods.name().equals("aParentMethod")))
                            .isTrue();
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((methods)-> methods.name().equals("toString")))
                            .isTrue();
                });

            });

            describe("for type variables and wildcards", () -> {

                describe("without upper bounds", () -> {

                    context().typeInstance(MethodsPerTypeTest::getUnboundedWildcardType);

                    it("includes all the methods from Object", () -> {
                        assertThat(context().typeInstance().methods().all()
                                .anyMatch((methods) -> methods.name().equals("equals")))
                                .isTrue();
                        assertThat(context().typeInstance().methods().all()
                                .anyMatch((methods) -> methods.name().equals("notify")))
                                .isTrue();
                    });
                });

                describe("with upper bounds", () -> {

                    context().typeInstance(MethodsPerTypeTest::getStringSubTypeWildcardType);

                    it("includes methods from extended type",()->{
                        assertThat(context().typeInstance().methods().all()
                                .anyMatch((methods) -> methods.name().equals("aPublicMethod")))
                                .isTrue();
                    });

                    it("includes inherited methods from the extended type",()->{
                        assertThat(context().typeInstance().methods().all()
                                .anyMatch((methods) -> methods.name().equals("aParentMethod")))
                                .isTrue();
                    });

                });
            });


            describe("for array types", () -> {
                context().typeInstance(() -> Diamond.of(String[].class));

                it("includes all methods from Object",()->{
                    assertThat(context().typeInstance().methods().all()
                            .anyMatch((methods) -> methods.name().equals("wait")))
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


        });

    }


    private static TypeInstance getUnboundedWildcardType(){
        return getTypeFrom(new ReferenceOf<List<?>>() {}).generics().typeArguments().findFirst().get();
    }

    private static TypeInstance getStringSubTypeWildcardType(){
        return getTypeFrom(new ReferenceOf<List<? extends ChildClass>>() {}).generics().typeArguments().findFirst().get();
    }

    private static TypeInstance getTypeFrom(ReferenceOf<?> reference) {
        AnnotatedType annotatedType = reference.getReferencedAnnotatedType();
        TypeInstance typeInstance = Diamond.types().from(annotatedType);
        return typeInstance;
    }
}
