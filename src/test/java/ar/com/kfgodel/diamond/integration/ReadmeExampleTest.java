package ar.com.kfgodel.diamond.integration;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Date: 16/10/19 - 00:26
 */
@RunWith(JavaSpecRunner.class)
public class ReadmeExampleTest extends JavaSpec<ReadmeExampleTestContext> {
  @Override
  public void define() {
    describe("diamond", () -> {

      it("adds a dsl over reflection API", () -> {
        final TypeInstance objectType = Diamond.of(Object.class);
        assertThat(objectType.names().canonicalName()).isEqualTo("java.lang.Object");
      });

      it("allows creating new instances easily", () -> {
        final Object createdObject = Diamond.of(Object.class)
          .newInstance();
        assertThat(createdObject).isNotNull();
      });

      it("offers a simplified no-checked-exception way to access class fields", () -> {
        final List<String> namesOfObjectFields = Diamond.of(Object.class)
          .fields().all()
          .map(TypeField::name)
          .collect(Collectors.toList());
        assertThat(namesOfObjectFields).isEmpty();
      });

      it("offers a simplified no-checked-exception way to access class methods", () -> {
        final List<String> namesOfObjectMethods = Diamond.of(Object.class)
          .methods().all()
          .map(TypeMethod::name)
          .collect(Collectors.toList());
        assertThat(namesOfObjectMethods).containsExactlyInAnyOrder(
          "finalize",
          "wait",
          "wait",
          "wait",
          "equals",
          "toString",
          "hashCode",
          "getClass",
          "clone",
          "registerNatives",
          "notify",
          "notifyAll"
        );
      });

      it("offers a simplified way of calling constructors and methods", () -> {
        final String createdString = (String) Diamond.of(String.class)
          .constructors().withNativeParameters(byte[].class)
          .unique().get()
          .invoke(new byte[0]);
        assertThat(createdString).isEmpty();
      });

    });

  }
}