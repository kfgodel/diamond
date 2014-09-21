package ar.com.kfgodel.diamond.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.ReferenceOf;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.testobjects.TestAnnotation1;
import ar.com.kfgodel.diamond.testobjects.TestAnnotation2;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests the representation of type variables
 * Created by kfgodel on 20/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class TypeVariableTest extends JavaSpec<DiamondTestContext> {

    @Override
    public void define() {
        describe("a type variable representation", () -> {
            beforeEach(()->{
                context().typeInstance(() -> createTypeVariable());
            });

            it("has a name", () -> {
                assertThat(context().typeInstance().name())
                        .isEqualTo("T");
            });
            it("has upper bounds", ()->{
                List<String> upperTypeNames = context().typeInstance().bounds().upper().map((upperBound)-> upperBound.name()).collect(Collectors.toList());
                assertThat(upperTypeNames)
                        .isEqualTo(Arrays.asList("Serializable", "Comparable"));
            });
            it("doesn't have lower bounds", ()->{
                assertThat(context().typeInstance().bounds().lower().count())
                        .isEqualTo(0);
            });

            it("can have attached annotations", ()->{
                assertThat(context().typeInstance().annotations().findFirst().get().annotationType())
                        .isEqualTo(TestAnnotation1.class);
            });
            it("bounds can have attached annotations too", ()->{
                Optional<TypeInstance> serializableType = context().typeInstance().bounds().upper().findFirst();
                Stream<Annotation> serializableAnnotations = serializableType.get().annotations();
                assertThat(serializableAnnotations.findFirst().get().annotationType())
                        .isEqualTo(TestAnnotation2.class);
            });

        });
    }


    /**
     * Creates the representation for a real type variable.<br>
     * The static keyword is needed to preserve type variable annotation (I don't know why).<br>
     *     If this method were non-static the annotation TestAnnotation1 is not preserved for reflection
     */
    public static <T extends @TestAnnotation2 Serializable & Comparable> TypeInstance createTypeVariable() {
        AnnotatedType referencedTypeVariable = new ReferenceOf<@TestAnnotation1 T>() {}.getReferencedAnnotatedType();
        TypeInstance typeInstance = Diamond.types().fromUnspecific(referencedTypeVariable);
        return typeInstance;
    }

}
