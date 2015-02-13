package ar.com.kfgodel.diamond.unit.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies correct behavior of instance of and assignment tests
 * Created by kfgodel on 02/02/15.
 */
@RunWith(JavaSpecRunner.class)
public class TypeOfTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {

        describe("instance test", () -> {
            
            it("allows to test wether an object is an instance of a given type",()->{
                Object anObject = new Object();
                
                TypeInstance objectType = Diamond.of(Object.class);
                boolean answer = objectType.isTypeFor(anObject);
                
                assertThat(answer).isTrue();
            });
            
            it("is even usable on arrays",()->{
                Object anObject = new int[]{1,2,4};

                TypeInstance objectType = Diamond.of(int[].class);
                boolean answer = objectType.isTypeFor(anObject);

                assertThat(answer).isTrue();
            });   
            
        });

        describe("type test", () -> {
            
            it("allows to check assignability to a type",()->{
                TypeInstance subtype = Diamond.of(ArrayList.class);
                TypeInstance superType = Diamond.of(List.class);
                
                boolean answer = subtype.isAssignableTo(superType);

                assertThat(answer).isTrue();
            });
            
            it("allows to check assignability from a type",()->{
                TypeInstance subtype = Diamond.of(ArrayList.class);
                TypeInstance superType = Diamond.of(List.class);

                boolean answer = superType.isAssignableFrom(subtype);

                assertThat(answer).isTrue();
            });
            
        });


    }
}