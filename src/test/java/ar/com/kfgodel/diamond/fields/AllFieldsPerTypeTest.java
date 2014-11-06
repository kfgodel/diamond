package ar.com.kfgodel.diamond.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.Mutability;
import ar.com.kfgodel.diamond.api.members.modifiers.Visibility;
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
 * This type tests the behavior for field retrieval in different types
 * Created by kfgodel on 12/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class AllFieldsPerTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("all fields", () -> {

            describe("for classes", () -> {

                context().typeInstance(() -> Diamond.of(ChildClass.class));

                it("includes public fields",()->{
                    assertThat(context().typeInstance().fields().all()
                            .anyMatch((field) -> field.modifiers().anyMatch(Visibility.PUBLIC)))
                            .isTrue();
                });

                it("includes protected fields",()->{
                    assertThat(context().typeInstance().fields().all()
                            .anyMatch((field) -> field.modifiers().anyMatch(Visibility.PROTECTED)))
                            .isTrue();
                });

                it("includes private fields",()->{
                    assertThat(context().typeInstance().fields().all()
                            .anyMatch((field)-> field.modifiers().anyMatch(Visibility.PRIVATE)))
                            .isTrue();
                });

                it("includes default fields",()->{
                    assertThat(context().typeInstance().fields().all()
                            .anyMatch((field)-> field.modifiers().anyMatch(Visibility.PACKAGE)))
                            .isTrue();
                });

                it("includes static fields",()->{
                    assertThat(context().typeInstance().fields().all()
                            .anyMatch((field)-> field.modifiers().anyMatch(Mutability.STATIC)))
                            .isTrue();
                });

                it("includes inherited fields",()->{
                    assertThat(context().typeInstance().fields().all()
                            .anyMatch((field)-> field.name().equals("aParentsPrivateField")))
                            .isTrue();
                    assertThat(context().typeInstance().fields().all()
                            .anyMatch((field)-> field.name().equals("aParentsPublicField")))
                            .isTrue();
                });

            });

            describe("for type variables and wildcards", () -> {

                describe("without upper bounds", () -> {

                    context().typeInstance(AllFieldsPerTypeTest::getUnboundedWildcardType);

                    it("is empty", () -> {
                        assertThat(context().typeInstance().fields().all().count())
                                .isEqualTo(0);
                    });
                });

                describe("with upper bounds", () -> {

                    context().typeInstance(AllFieldsPerTypeTest::getChildClassSubTypeWildcardType);

                    it("includes fields from the upper type",()->{
                        assertThat(context().typeInstance().fields().all()
                                .anyMatch((field) -> field.name().equals("aPrivateField")))
                                .isTrue();
                    });

                    it("includes inherited fields from the upper type hierarchy",()->{
                        assertThat(context().typeInstance().fields().all()
                                .anyMatch((field) -> field.name().equals("aParentsPrivateField")))
                                .isTrue();
                    });
                });

            });


            describe("for array types", () -> {
                context().typeInstance(() -> Diamond.of(String[].class));

                it("is empty",()->{
                    assertThat(context().typeInstance().fields().all().count())
                            .isEqualTo(0);
                });

            });


            describe("for primitive types", () -> {
                context().typeInstance(() -> Diamond.of(int.class));

                it("is empty",()->{
                    assertThat(context().typeInstance().fields().all().count())
                            .isEqualTo(0);
                });

            });

            describe("for parameterized types", ()->{
                context().typeInstance(AllFieldsPerTypeTest::getParameterizedParentClass);

                it("includes the same fields as the raw class",()->{
                    assertThat(context().typeInstance().fields().all().collect(Collectors.toList()))
                            .isEqualTo(Diamond.of(ParentClass.class).fields().all().collect(Collectors.toList()));
                });
            });

        });

    }


    private static TypeInstance getUnboundedWildcardType(){
        TypeInstance listType = getTypeFrom(new ReferenceOf<List<?>>() {});
        TypeInstance unboundedWildcard = listType.generics().arguments().findFirst().get();
        return unboundedWildcard;
    }

    private static TypeInstance getChildClassSubTypeWildcardType(){
        return getTypeFrom(new ReferenceOf<List<? extends ChildClass>>() {}).generics().arguments().findFirst().get();
    }

    private static <A extends ChildClass & Collection> TypeInstance getChildClassAndNumberSubtypeVariableType(){
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
