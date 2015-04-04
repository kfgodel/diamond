package ar.com.kfgodel.diamond.unit.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.fields.RedefiningFieldTestObject;
import ar.com.kfgodel.nary.exceptions.MoreThanOneElementException;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;
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
                ar.com.kfgodel.optionals.Optional<TypeField> matchingFields = context().typeInstance().fields().named("uniqueField");
                assertThat(matchingFields.isPresent()).isTrue();
            });
            
            it("throws exception if more than one match when unique assumed",()->{
                try {
                    context().typeInstance().fields().named("duplicatedField").isPresent();
                    failBecauseExceptionWasNotThrown(MoreThanOneElementException.class);
                } catch (MoreThanOneElementException e) {
                    assertThat(e).hasMessage("There's more than one element in the stream to create an optional: [public int duplicatedField /* RedefiningFieldTestObject */, protected int duplicatedField /* RedefinedFieldTestObject */]");
                }
            });
            
            
            it("can assume a non optional occurrence",()->{
                TypeField matchingField = context().typeInstance().fields().named("uniqueField").get();
                assertThat(matchingField).isNotNull();
            });
            
            it("throws exception if no match for non optional unique",()->{
                try {
                    context().typeInstance().fields().named("nonExistingField").get();
                    failBecauseExceptionWasNotThrown(NoSuchElementException.class);
                } catch (NoSuchElementException e) {
                    assertThat(e).hasMessage("No value present");
                }
            });
        });

    }
}