package ar.com.kfgodel.diamond.naming;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.ReferenceOf;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.testobjects.TestAnnotation1;
import ar.com.kfgodel.diamond.testobjects.TestAnnotation2;
import org.junit.runner.RunWith;

import java.lang.reflect.AnnotatedType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests the representation of class names
 * Created by kfgodel on 21/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class TypeNamingTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("types", ()->{

            it("have a name that is equal to their shortname", () -> {
                TypeInstance objectType = Diamond.of(Object.class);
                assertThat(objectType.name()).isEqualTo(objectType.names().shortName());
            });

            describe("for classes / parameterized types", () -> {
                beforeEach(() -> {
                    context().typeInstance(this::getStringListType);
                });

                it("shortname is the simple name", () -> {
                    assertThat(context().typeInstance().names().shortName())
                            .isEqualTo("List");
                });
                it("classloader name is the class name", () -> {
                    assertThat(context().typeInstance().names().classloaderName())
                            .isEqualTo("java.util.List");
                });
                it("canonical name is the class canonical name", () -> {
                    assertThat(context().typeInstance().names().canonicalName())
                            .isEqualTo("java.util.List");
                });
                it("typeName is the class type name", () -> {
                    assertThat(context().typeInstance().names().typeName())
                            .isEqualTo("java.util.List<java.lang.String>");
                });
                xit("declaration name is the soure declaration equivalent", () -> {
                    assertThat(context().typeInstance().names().declarationName())
                            .isEqualTo("@TestAnnotation1 java.util.List< @TestAnnotation2 java.lang.String>");
                });
            });

            describe("for array types", () -> {
                beforeEach(() -> {
                    context().typeInstance(this::getStringArrayType);
                });

                it("shortname is the simple name", () -> {
                    assertThat(context().typeInstance().names().shortName())
                            .isEqualTo("String[]");
                });
                it("classloader name is the class name", () -> {
                    assertThat(context().typeInstance().names().classloaderName())
                            .isEqualTo("[Ljava.lang.String;");
                });
                it("canonical name is the class canonical name", () -> {
                    assertThat(context().typeInstance().names().canonicalName())
                            .isEqualTo("java.lang.String[]");
                });
                it("typeName is the class type name", () -> {
                    assertThat(context().typeInstance().names().typeName())
                            .isEqualTo("java.lang.String[]");
                });
                xit("declaration name is the soure declaration equivalent", () -> {
                    assertThat(context().typeInstance().names().declarationName())
                            .isEqualTo("@TestAnnotation2 String @TestAnnotation1[]");
                });
            });

            describe("for type variables", () -> {
                beforeEach(() -> {
                    context().typeInstance(TypeNamingTest::getTypeVariableA);
                });

                it("shortname is the variable name", () -> {
                    assertThat(context().typeInstance().names().shortName())
                            .isEqualTo("A");
                });
                it("classloader name is typeName", () -> {
                    assertThat(context().typeInstance().names().classloaderName())
                            .isEqualTo(context().typeInstance().names().typeName());
                });
                it("canonical name is the typeName", () -> {
                    assertThat(context().typeInstance().names().canonicalName())
                            .isEqualTo(context().typeInstance().names().typeName());
                });
                it("typeName is the type name (for some reason it doesn't include bounds)", () -> {
                    assertThat(context().typeInstance().names().typeName())
                            .isEqualTo("A");
                });
                xit("declaration name is the soure declaration equivalent", () -> {
                    assertThat(context().typeInstance().names().declarationName())
                            .isEqualTo("@TestAnnotation1 A extends @TestAnnotation2 String");
                });
            });

            describe("for type wildcards", () -> {
                beforeEach(() -> {
                    context().typeInstance(this::getWildcardType);
                });

                it("shortname is the wildcard symbol", () -> {
                    assertThat(context().typeInstance().names().shortName())
                            .isEqualTo("?");
                });
                it("classloader name is typeName", () -> {
                    assertThat(context().typeInstance().names().classloaderName())
                            .isEqualTo(context().typeInstance().names().typeName());
                });
                it("canonical name is the typeName", () -> {
                    assertThat(context().typeInstance().names().canonicalName())
                            .isEqualTo(context().typeInstance().names().typeName());
                });
                it("typeName is the type name", () -> {
                    assertThat(context().typeInstance().names().typeName())
                            .isEqualTo("? extends java.lang.String");
                });
                xit("declaration name is the soure declaration equivalent", () -> {
                    assertThat(context().typeInstance().names().declarationName())
                            .isEqualTo("@TestAnnotation1 ? extends @TestAnnotation2 String");
                });
            });


        });
    }

    private TypeInstance getStringListType(){
        return getTypeFrom(new ReferenceOf<@TestAnnotation1 List< @TestAnnotation2 String>>() {});
    }
    private TypeInstance getStringArrayType(){
        return getTypeFrom(new ReferenceOf<@TestAnnotation2 String @TestAnnotation1[]>() {});
    }
    private static <A extends @TestAnnotation2 String> TypeInstance getTypeVariableA(){
        return getTypeFrom(new ReferenceOf<@TestAnnotation1 A>() {});
    }
    private TypeInstance getWildcardType(){
        return getTypeFrom(new ReferenceOf<List<@TestAnnotation1 ? extends @TestAnnotation2 String>>() {}).typeArguments().findFirst().get();
    }


    private static TypeInstance getTypeFrom(ReferenceOf<?> reference) {
        AnnotatedType annotatedType = reference.getReferencedAnnotatedType();
        TypeInstance typeInstance = Diamond.types().from(annotatedType);
        return typeInstance;
    }

}
