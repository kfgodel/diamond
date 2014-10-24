package ar.com.kfgodel.diamond.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.testobjects.accessors.FieldAccessorTestObject;
import org.junit.runner.RunWith;

/**
 * This type verifies the behavior of fields as value accessors
 * Created by kfgodel on 23/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FieldAccessorTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("field as static accessor", ()->{

            context().object(FieldAccessorTestObject::new);
            context().typeInstance(()-> Diamond.of(context().object().getClass()));

            it("can get the value from an instance", () -> {
//                TypeField field = context().typeInstance().fields();
            });

            it("can set the value to an instance",()->{

            });
        });
    }
}