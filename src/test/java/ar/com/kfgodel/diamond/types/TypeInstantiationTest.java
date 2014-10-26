package ar.com.kfgodel.diamond.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.testobjects.constructors.NoNiladicTestObject;
import ar.com.kfgodel.diamond.testobjects.modifiers.AbstractMemberTestObject;
import ar.com.kfgodel.diamond.testobjects.modifiers.PublicMembersTestObject;
import org.junit.runner.RunWith;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the instance creation from a given type
 * Created by kfgodel on 26/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class TypeInstantiationTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("A Type's new instance", () -> {
            
            it("uses the niladic constructor",()->{
                TypeInstance type = Diamond.of(PublicMembersTestObject.class);

                Object createdInstance = type.newInstance();

                assertThat(createdInstance).isNotNull();
                assertThat(createdInstance).isInstanceOf(PublicMembersTestObject.class);
            });

            it("throws an exception if type is abstract",()->{
                TypeInstance type = Diamond.of(AbstractMemberTestObject.class);

                try {
                    type.newInstance();
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("Abstract type cannot be created: protected ar.com.kfgodel.diamond.testobjects.modifiers.AbstractMemberTestObject()");
                }
            });

            it("throws an exception if type doesn't have a niladic constructor",()->{
                TypeInstance type = Diamond.of(NoNiladicTestObject.class);

                try {
                    type.newInstance();
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("Type[ar.com.kfgodel.diamond.testobjects.constructors.NoNiladicTestObject] doesn't have a no-arg constructor to create the instance from");
                }
            });

            it("can be used as a supplier",()->{
                Supplier<Object> supplier  = Diamond.of(PublicMembersTestObject.class);

                Object createdInstance = supplier.get();

                assertThat(createdInstance).isNotNull();
                assertThat(createdInstance).isInstanceOf(PublicMembersTestObject.class);
            });
        });

    }
}