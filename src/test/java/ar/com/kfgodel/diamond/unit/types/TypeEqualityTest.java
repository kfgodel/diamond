package ar.com.kfgodel.diamond.unit.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.impl.types.description.TypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.equality.TypeEquality;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import org.junit.runner.RunWith;

import java.lang.reflect.AnnotatedType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests equality definitions on different types
 * Created by kfgodel on 03/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class TypeEqualityTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("type equality", () -> {

            it("is reified by TypeEquality", () -> {
                TypeInstance objectType = getObjectType();
                assertThat(TypeEquality.INSTANCE.areEquals(objectType, objectType)).
                        isTrue();
            });

            describe("for type variables", () -> {
                it("is true if same name and bounds",()->{
                    TypeInstance aTypeVariable = getTypeVariableASubTypeOfString();
                    TypeInstance otherTypeVariable = getTypeVariableASubTypeOfString();
                    assertThat(aTypeVariable).isEqualTo(otherTypeVariable);
                });

                it("is false for two different variable names", () -> {
                    TypeInstance typeVariableA = getTypeVariableA();
                    TypeInstance typeVariableB = getTypeVariableB();
                    assertThat(typeVariableA).isNotEqualTo(typeVariableB);
                });

                it("is false if same name and different upper bound", () -> {
                    TypeInstance stringSubtype = getTypeVariableASubTypeOfString();
                    TypeInstance numberSubtype = getTypeVariableASubTypeOfNumber();
                    assertThat(stringSubtype).isNotEqualTo(numberSubtype);
                });
            });

            describe("for wildcards", () -> {
                it("is true if same bounds",()->{
                    TypeInstance aWildcard = getWildcardSubTypeOfString();
                    TypeInstance anotherWildcard = getWildcardSubTypeOfString();
                    assertThat(aWildcard).isEqualTo(anotherWildcard);
                });

                it("is false if different upper bounds", () -> {
                    TypeInstance stringSubtype = getWildcardSubTypeOfString();
                    TypeInstance numberSubtype = getWildcardSubTypeOfNumber();
                    assertThat(stringSubtype).isNotEqualTo(numberSubtype);
                });

                it("is false if different lower bounds", () -> {
                    TypeInstance stringSupertype = getWildcardSupertypeOfString();
                    TypeInstance numberSupertype = getWildcardSupertypeOfNumber();
                    assertThat(stringSupertype).isNotEqualTo(numberSupertype);
                });
            });

            describe("for classes", () -> {
                it("is true if same canonical name and type arguments",()->{
                    TypeInstance aClassType = getStringType();
                    TypeInstance anotherClassType = getStringType();
                    assertThat(aClassType).isEqualTo(anotherClassType);
                });

                it("is false for classes with different canonical name", () -> {
                    TypeInstance objectType = getObjectType();
                    TypeInstance stringType = getStringType();
                    assertThat(objectType).isNotEqualTo(stringType);
                });

                it("is false for classes with same name and different type arguments", () -> {
                    TypeInstance stringList = getStringListType();
                    TypeInstance numberList = getNumberListType();
                    assertThat(stringList).isNotEqualTo(numberList);
                });
            });

            describe("for array types", () -> {
                it("is true if same component type",()->{
                    TypeInstance anArrayType = getStringArrayType();
                    TypeInstance anotherArrayType = getStringArrayType();
                    assertThat(anArrayType).isEqualTo(anotherArrayType);
                });
                it("is false if different component type", () -> {
                    TypeInstance stringArray = getStringArrayType();
                    TypeInstance numberArray = getNumberArrayType();
                    assertThat(stringArray).isNotEqualTo(numberArray);
                });
            });

            describe("hashcode", () -> {

                it("is equal if type are equals",()->{
                    //Creation from description is not cached
                    TypeDescription stringDescription = TypeDescriptor.INSTANCE.describe(String.class);
                    TypeInstance one = Diamond.types().fromDescription(stringDescription);
                    TypeInstance other = Diamond.types().fromDescription(stringDescription);

                    assertThat(one).isNotSameAs(other);
                    assertThat(one).isEqualTo(other);
                    assertThat(one.hashCode()).isEqualTo(other.hashCode());
                });
            });

        });
    }

    public static<A> TypeInstance getTypeVariableA() {
        return getTypeFrom(new ReferenceOf<A>() {});
    }
    public static<B> TypeInstance getTypeVariableB() {
        return getTypeFrom(new ReferenceOf<B>() {});
    }

    private static TypeInstance getTypeFrom(ReferenceOf<?> reference) {
        AnnotatedType annotatedType = reference.getReferencedAnnotatedType();
        TypeInstance typeInstance = Diamond.types().from(annotatedType);
        return typeInstance;
    }

    public TypeInstance getObjectType() {
        return Diamond.of(Object.class);
    }
    public TypeInstance getStringType() {
        return Diamond.of(String.class);
    }

    public static <A extends String> TypeInstance getTypeVariableASubTypeOfString() {
        return getTypeFrom(new ReferenceOf<A>() {});
    }

    public static <A extends Number> TypeInstance getTypeVariableASubTypeOfNumber() {
        return getTypeFrom(new ReferenceOf<A>() {});
    }

    private TypeInstance getWildcardSubTypeOfString() {
        return getTypeFrom(new ReferenceOf<List<? extends String>>() {}).generics().arguments().findFirst().get();
    }

    private TypeInstance getWildcardSubTypeOfNumber() {
        return getTypeFrom(new ReferenceOf<List<? extends Number>>() {}).generics().arguments().findFirst().get();
    }

    private TypeInstance getWildcardSupertypeOfString() {
        return getTypeFrom(new ReferenceOf<List<? super String>>() {}).generics().arguments().findFirst().get();
    }

    private TypeInstance getWildcardSupertypeOfNumber() {
        return getTypeFrom(new ReferenceOf<List<? super Number>>() {}).generics().arguments().findFirst().get();
    }

    private TypeInstance getStringListType(){
        return getTypeFrom(new ReferenceOf<List<String>>() {});
    }

    private TypeInstance getNumberListType(){
        return getTypeFrom(new ReferenceOf<List<Number>>() {});
    }

    private TypeInstance getStringArrayType(){
        return getTypeFrom(new ReferenceOf<String[]>() {});
    }

    private TypeInstance getNumberArrayType(){
        return getTypeFrom(new ReferenceOf<Number[]>() {});
    }

}
