package ar.com.kfgodel.diamond.unit.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kfgodel on 02/02/15.
 */
@RunWith(JavaSpecRunner.class)
public class TypeInstanceOfTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {

        describe("instance test", () -> {
            
            it("allows to test wether an object is an instance of a given type",()->{
                Object anObject = new Object();
                
                TypeInstance objectType = Diamond.of(Object.class);
                boolean answer = objectType.isTypeFor(anObject);
                
                assertThat(answer).isTrue();
            });
            
        });

    }
}