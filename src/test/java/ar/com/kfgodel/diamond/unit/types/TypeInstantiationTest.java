package ar.com.kfgodel.diamond.unit.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.constructors.NoNiladicTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.AbstractMemberTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import org.junit.runner.RunWith;

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

                assertThat(createdInstance).isInstanceOf(PublicMembersTestObject.class);
            });

            it("throws an exception if type is abstract",()->{
                TypeInstance type = Diamond.of(AbstractMemberTestObject.class);

                try {
                    type.newInstance();
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("Abstract type cannot be created: protected ar.com.kfgodel.diamond.unit.testobjects.modifiers.AbstractMemberTestObject()");
                }
            });

            it("throws an exception if type doesn't have a niladic constructor",()->{
                TypeInstance type = Diamond.of(NoNiladicTestObject.class);

                try {
                    type.newInstance();
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("Type[NoNiladicTestObject @ ar.com.kfgodel.diamond.unit.testobjects.constructors] doesn't have a no-arg constructor to create the instance from");
                }
            });
            
        });

    }
}