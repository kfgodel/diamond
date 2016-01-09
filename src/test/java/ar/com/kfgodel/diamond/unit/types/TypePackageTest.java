package ar.com.kfgodel.diamond.unit.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.impl.types.packages.PackageDescriptor;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass;
import ar.com.kfgodel.nary.api.Nary;
import org.junit.runner.RunWith;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of the package related to a type
 * Created by kfgodel on 05/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class TypePackageTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a type's package", () -> {

            describe("information", () -> {

                context().typePackage(() -> Diamond.of(ChildClass.class).declaredPackage().get());

                it("has the name of the containing package", () -> {
                    String packageName = context().typePackage().name();

                    assertThat(packageName).isEqualTo("ar.com.kfgodel.diamond.unit.testobjects.lineage");
                });

                it("has the annotations added to the package",()->{
                    List<String> annotationNames = context().typePackage().annotations().map(Annotation::annotationType).map(Class::getSimpleName).collect(Collectors.toList());
                    assertThat(annotationNames)
                            .isEqualTo(Arrays.asList("TestAnnotation1"));
                });

            });


            describe("for classes", () -> {

                it("is always present", () -> {
                    Nary<TypePackage> typePackage = Diamond.of(ChildClass.class).declaredPackage();

                    assertThat(typePackage.isPresent()).isTrue();
                });   
            });

            describe("for type variables or wildcards", () -> {

                it("is always absent",()->{
                    Nary<TypePackage> typePackage = getChildClassSubTypeWildcardType().declaredPackage();

                    assertThat(typePackage.isPresent()).isFalse();
                });
            });

            describe("for array types", () -> {
                it("is always absent",()->{
                    Nary<TypePackage> typePackage = Diamond.of(String[].class).declaredPackage();

                    assertThat(typePackage.isPresent()).isFalse();
                });
            });

            describe("equality", () -> {

                context().typePackage(() -> Diamond.of(ChildClass.class).declaredPackage().get());

                it("is true if both are named equal",()->{
                    TypePackage otherPackage = Diamond.packages().fromDescription(PackageDescriptor.INSTANCE.describe(Package.getPackage("ar.com.kfgodel.diamond.unit.testobjects.lineage")));
                    assertThat(context().typePackage()).isEqualTo(otherPackage);
                });

                it("is false if name is different",()->{
                    TypePackage otherPackage = Diamond.packages().named("java.lang");
                    assertThat(context().typePackage()).isNotEqualTo(otherPackage);
                });
            });


        });

    }

    private static TypeInstance getChildClassSubTypeWildcardType(){
        return getTypeFrom(new ReferenceOf<List<? extends ChildClass>>() {}).generics().arguments().findFirst().get();
    }

    private static TypeInstance getTypeFrom(ReferenceOf<?> reference) {
        AnnotatedType annotatedType = reference.getReferencedAnnotatedType();
        TypeInstance typeInstance = Diamond.types().from(annotatedType);
        return typeInstance;
    }

}
