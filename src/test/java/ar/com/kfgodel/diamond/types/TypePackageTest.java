package ar.com.kfgodel.diamond.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.testobjects.lineage.ChildClass;
import org.junit.runner.RunWith;

import java.lang.annotation.Annotation;
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

            describe("for classes", () -> {

                context().typePackage(() -> Diamond.of(ChildClass.class).declaredPackage().get());

                it("has the name of the containing package", () -> {
                    String packageName = context().typePackage().name();

                    assertThat(packageName).isEqualTo("ar.com.kfgodel.diamond.testobjects.lineage");
                });   
                
                it("has the annotations added to the package",()->{
                    List<String> annotationNames = context().typePackage().annotations().map(Annotation::annotationType).map(Class::getSimpleName).collect(Collectors.toList());
                    assertThat(annotationNames)
                            .isEqualTo(Arrays.asList("TestAnnotation1"));
                });   
            });


        });

    }
}
