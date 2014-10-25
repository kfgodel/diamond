package ar.com.kfgodel.diamond.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.testobjects.fields.RedefiningFieldTestObject;
import org.junit.runner.RunWith;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the expected behavior for accessing fields by name
 * Created by kfgodel on 23/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FieldByNameTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("field accessed by name", () -> {

            context().object(RedefiningFieldTestObject::new);
            context().typeInstance(()-> Diamond.of(context().object().getClass()));

            it("can have no match",()->{
                Stream<TypeField> matchingFields = context().typeInstance().fields().named("nonExistingField");
                assertThat(matchingFields.count()).isEqualTo(0);
            });

            it("can have more than one match",()->{
                Stream<TypeField> matchingFields = context().typeInstance().fields().named("duplicatedField");
                assertThat(matchingFields.count()).isEqualTo(2);
            });   
            
            it("can assume only one optional occurrence",()->{
                Optional<TypeField> matchingFields = context().typeInstance().fields().uniqueNamed("uniqueField");
                assertThat(matchingFields.isPresent()).isTrue();
            });
            
            it("throws exception if more than one match for optional unique",()->{
                try {
                    context().typeInstance().fields().uniqueNamed("duplicatedField");
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("There's more than one field named \"duplicatedField\"");
                }
            });
            
            
            it("can assume a non optional occurrence",()->{
                TypeField matchingField = context().typeInstance().fields().existingNamed("uniqueField");
                assertThat(matchingField).isNotNull();
            });
            
            it("throws exception if no match for non optional unique",()->{
                try {
                    context().typeInstance().fields().existingNamed("nonExistingField");
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("There's no field named \"nonExistingField\"");
                }
            });
        });

    }
}