package ar.com.kfgodel.diamond.unit.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.AnnotatedType;
import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the behavior of a type as a functional element
 * Created by kfgodel on 26/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FunctionalTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("type as function", () -> {

            it("is a supplier of new instances",()->{
                Supplier<Object> supplier = Diamond.of(PublicMembersTestObject.class);

                Object createdInstance = supplier.get();

                assertThat(createdInstance).isInstanceOf(PublicMembersTestObject.class);
            });

            it("throws an error for type variables or wildcards",()->{
                Supplier<Object> supplier = getChildClassSubTypeWildcardType();

                try {
                    supplier.get();
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("Type[? extends ChildClass] doesn't have a no-arg constructor to create the instance from");
                }
            });


            it("throws an error for array types or other non niladic constructor types",()->{
                Supplier<Object> supplier = Diamond.of(String [].class);

                try {
                    supplier.get();
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("Type[String[]] doesn't have a no-arg constructor to create the instance from");
                }
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