package ar.com.kfgodel.diamond.unit.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.kinds.Kinds;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.TestEnum;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the different kind of types behavior
 * Created by kfgodel on 03/02/15.
 */
@RunWith(JavaSpecRunner.class)
public class KindsTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a type kind", () -> {
            
            it("is a categorization used to group similar types without a type hierarchy",()->{
                TypeInstance objectType = Diamond.of(Object.class);
                
                boolean answer = objectType.is(Kinds.REFERENCE);
                
                assertThat(answer).isTrue();
            });
            
            it("a type can have more than one kind",()->{
                TypeInstance listType = Diamond.of(List.class);

                List<Kind> kindList = listType.kinds().collect(Collectors.toList());

                assertThat(kindList).contains(Kinds.REFERENCE);
                assertThat(kindList).contains(Kinds.CONTAINER);
            });

            describe("allows discrimination of", () -> {
                
                it("primitive types",()->{
                    TypeInstance objectType = Diamond.of(int.class);

                    boolean answer = objectType.is(Kinds.PRIMITIVE);

                    assertThat(answer).isTrue();
                });

                it("reference types (a.k.a non-primitives)",()->{
                    TypeInstance objectType = Diamond.of(Object.class);

                    boolean answer = objectType.is(Kinds.REFERENCE);

                    assertThat(answer).isTrue();
                });

                it("reference types (a.k.a non-primitives)",()->{
                    TypeInstance objectType = Diamond.of(Object.class);

                    boolean answer = objectType.is(Kinds.REFERENCE);

                    assertThat(answer).isTrue();
                });

                it("array types",()->{
                    TypeInstance objectType = Diamond.of(int[].class);

                    boolean answer = objectType.is(Kinds.ARRAY);

                    assertThat(answer).isTrue();
                });

                it("value types (numbers and text)",()->{
                    TypeInstance objectType = Diamond.of(String.class);

                    boolean answer = objectType.is(Kinds.VALUE);

                    assertThat(answer).isTrue();
                });

                it("boolean types (boxed and unboxed)",()->{
                    TypeInstance objectType = Diamond.of(boolean.class);

                    boolean answer = objectType.is(Kinds.BOOLEAN);

                    assertThat(answer).isTrue();
                });
                
                it("numeric types (boxed and unboxed)",()->{
                    TypeInstance objectType = Diamond.of(long.class);

                    boolean answer = objectType.is(Kinds.NUMERIC);

                    assertThat(answer).isTrue();
                });

                it("text types",()->{
                    TypeInstance objectType = Diamond.of(StringBuffer.class);

                    boolean answer = objectType.is(Kinds.TEXT);

                    assertThat(answer).isTrue();
                });

                it("enum types",()->{
                    TypeInstance objectType = Diamond.of(TestEnum.class);

                    boolean answer = objectType.is(Kinds.ENUM);

                    assertThat(answer).isTrue();
                });

                it("container types",()->{
                    TypeInstance objectType = Diamond.of(Map.class);

                    boolean answer = objectType.is(Kinds.CONTAINER);

                    assertThat(answer).isTrue();
                });
                it("interface types",()->{
                    TypeInstance objectType = Diamond.of(List.class);

                    boolean answer = objectType.is(Kinds.INTERFACE);

                    assertThat(answer).isTrue();
                });
                it("class types",()->{
                    TypeInstance objectType = Diamond.of(ArrayList.class);

                    boolean answer = objectType.is(Kinds.CLASS);

                    assertThat(answer).isTrue();
                });
                it("annotation types",()->{
                    TypeInstance objectType = Diamond.of(Documented.class);

                    boolean answer = objectType.is(Kinds.ANNOTATION);

                    assertThat(answer).isTrue();
                });
                it("anonymous types",()->{
                    TypeInstance objectType = Diamond.of(new Runnable(){
                        @Override
                        public void run() {
                        }
                    }.getClass());

                    boolean answer = objectType.is(Kinds.ANONYMOUS);

                    assertThat(answer).isTrue();
                });
            });


        });

    }
}