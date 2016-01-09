package ar.com.kfgodel.diamond.unit.strings;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiers;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.constructors.ConstructorAccessTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.fields.BoundFieldTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.methods.BoundMethodTestObject;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the debug print method representation for every diamond entity
 * Created by kfgodel on 09/01/16.
 */
@RunWith(JavaSpecRunner.class)
public class ToStringTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("toString() method", () -> {
      context().toStringResult(() -> context().object().toString());

      describe("for packages", () -> {
        context().object(() -> Diamond.packages().named("java.lang"));

        it("is the full name",()->{
          assertThat(context().toStringResult()).isEqualTo("java.lang");
        });
      });

      describe("for classes", () -> {
        context().object(() -> Diamond.of(Object.class));

        it("is simple name @ package for classes", () -> {
          assertThat(context().toStringResult()).isEqualTo("Object @ java.lang");
        });
      });

      describe("for array types", () -> {
        context().object(()-> Diamond.of(String[][].class));
        
        it("is component dimensions",()->{
          assertThat(context().toStringResult()).isEqualTo("String[][]");
        });   
      });

      describe("for generic types", () -> {
        context().object(()-> Diamond.types().from(new ReferenceOf<Map<String, Integer>>() {}.getReferencedAnnotatedType()));

        it("is name <arguments> @ package",()->{
          assertThat(context().toStringResult()).isEqualTo("Map<String, Integer> @ java.util");
        });   
      });

      describe("for variable types", () -> {
        context().object(()->
          Diamond.types().from(new ReferenceOf<List<? extends String>>() {}.getReferencedAnnotatedType())
          .generics().arguments().findFirst().get()
        );

        it("is name and bounds",()->{
          assertThat(context().toStringResult()).isEqualTo("? extends String");
        });
      });


      describe("for fields", () -> {
        context().object(()-> Diamond.of(BoundFieldTestObject.class).fields().named("field").get());
        
        it("is name @ class",()->{
          assertThat(context().toStringResult()).isEqualTo("field @ BoundFieldTestObject");
        });

        describe("when bound", () -> {
          context().object(()-> Diamond.of(BoundFieldTestObject.class).fields().named("field").get()
          .bindTo(new BoundFieldTestObject()));
          
          it("is name @ instance",()->{
            assertThat(context().toStringResult()).isEqualTo("field @@ BoundFieldTestObject instance");
          });   
        });

      });

      describe("for methods", () -> {
        context().object(()-> Diamond.of(BoundMethodTestObject.class).methods().named("biconsumer").get());

        it("is name (arg types) @ class",()->{
          assertThat(context().toStringResult()).isEqualTo("biconsumer(int, int) @ BoundMethodTestObject");
        });

        describe("when bound", () -> {
          context().object(()-> Diamond.of(BoundMethodTestObject.class).methods().named("biconsumer").get()
            .bindTo(new BoundMethodTestObject()));

          it("is name (arg types) @ instance",()->{
            assertThat(context().toStringResult()).isEqualTo("biconsumer(int, int) @@ BoundMethodTestObject instance");
          });
        });

      });

      describe("for constructors", () -> {
        context().object(()-> Diamond.of(ConstructorAccessTestObject.class).constructors().withParameters(Diamond.of(Integer.class)).get());

        it("is name (arg types) @ package",()->{
          assertThat(context().toStringResult()).isEqualTo("ConstructorAccessTestObject(Integer) @ ar.com.kfgodel.diamond.unit.testobjects.constructors");
        });
      });

      describe("for method parameters", () -> {
        context().object(()-> Diamond.of(Object.class).methods().named("equals").get().parameters().get() );
        
        it("is the type and name",()->{
          // Depending on the JDK version and the compilation config the name varies
          boolean matchesTheExpectedName =
            context().toStringResult().equals("Object arg0") ||
              context().toStringResult().equals("Object obj");
          assertThat(matchesTheExpectedName).isTrue();
        });
      });

      describe("for modifiers", () -> {
        context().object(()-> Modifiers.TRANSIENT);

        it("is the declaration",()->{
            assertThat(context().toStringResult()).isEqualTo("transient");
        });
      });


    });
  }
}