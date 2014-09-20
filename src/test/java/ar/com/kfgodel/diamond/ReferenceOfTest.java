package ar.com.kfgodel.diamond;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.ReferenceOf;
import ar.com.kfgodel.diamond.testobjects.UnnecessaryIntermediateClass;
import org.junit.runner.RunWith;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type tests type references
 * Created by kfgodel on 20/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class ReferenceOfTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("TypeReferences", ()->{

            it("can reference a non parameterized type", ()->{
                Class<Object> referencedType = new ReferenceOf<Object>(){}.getReferencedClass();
                assertThat(referencedType).isEqualTo(Object.class);
            });

            it("can reference a parameterized type", ()->{
                ParameterizedType referencedType = new ReferenceOf<List<String>>(){}.getReferencedParameterizedType();
                assertThat(referencedType.getRawType()).isEqualTo(List.class);
                assertThat(referencedType.getActualTypeArguments()[0]).isEqualTo(String.class);
            });

            it("can reference a type variable", ()->{
                TypeVariable referencedType = referenceATypeVariable(null);
                assertThat(referencedType.getName()).isEqualTo("R");
                assertThat(referencedType.getBounds()[0].getTypeName()).isEqualTo("T");
            });

            it("can reference a generic array type",()->{
                GenericArrayType referencedType = new ReferenceOf<List<String>[]>(){}.getGenericArrayType();
                assertThat(referencedType.getGenericComponentType().getTypeName())
                        .isEqualTo("java.util.List<java.lang.String>");
            });

            it("can answer if reference type corresponds to a specific type", ()->{
                ReferenceOf<Object> reference = new ReferenceOf<Object>() {};
                assertThat(reference.referencesAClass()).isEqualTo(true);
                assertThat(reference.referencesAParameterizedType()).isEqualTo(false);
                assertThat(reference.referencesATypeVariable()).isEqualTo(false);
                assertThat(reference.referencesAWildCard()).isEqualTo(false);
                assertThat(reference.referencesAGenericArrayType()).isEqualTo(false);
            });

            it("must have a direct subclass", ()->{
                try {
                    new UnnecessaryIntermediateClass<Object>(){};
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("ReferenceOf should have a direct subclass (no multi-class hierarchy supported");
                }
            });

            it("must be parameterized", ()->{
                try {
                    new ReferenceOf(){};
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("ReferenceOf must be parameterized with a generic type to be used");
                }
            });
        });
    }

    private<T, R extends T> TypeVariable referenceATypeVariable(R optional) {
        return new ReferenceOf<R>(){}.getReferencedTypeVariable();
    }


}
