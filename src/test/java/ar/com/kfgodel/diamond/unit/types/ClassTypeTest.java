package ar.com.kfgodel.diamond.unit.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass;
import ar.com.kfgodel.nary.api.Unary;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type defines specs for test
 * Created by kfgodel on 19/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class ClassTypeTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a class instance", () -> {

      beforeEach(() -> {
        context().typeInstance(() -> Diamond.of(ChildClass.class));
      });

      describe("naming", () -> {
        it("has a simple short name", () -> {
          assertThat(context().typeInstance().name())
            .isEqualTo("ChildClass");
        });

        it("has other names", () -> {
          TypeNames classNames = context().typeInstance().names();
          assertThat(classNames.commonName())
            .isEqualTo("ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass");
        });
      });

      describe("inheritance", () -> {

        it("has a lineage with its type ancestors", () -> {
          TypeLineage typeLineage = context().typeInstance().hierarchy().lineage();
          assertThat(typeLineage.highestAncestor().name())
            .isEqualTo("Object");
        });

        /**
         * This is the runtime parent type
         */
        it("has a super class", () -> {
          String superClassName = context().typeInstance()
            .runtime().hierarchy().superclass()
            .map(TypeInstance::name)
            .unique().get();
          assertThat(superClassName).isEqualTo("ParentClass");
        });

        /**
         * This is the compile time parent type
         */
        it("has an extended type", () -> {
          String extendedTypeName = context().typeInstance()
            .hierarchy().extendedType()
            .map(Named::name)
            .get();
          assertThat(extendedTypeName).isEqualTo("ParentClass");
        });

        /**
         * This is the runtime interface types
         */
        it("has several interfaces", () -> {
          List<String> interfaces = context().typeInstance().runtime().hierarchy().interfaces().map(Named::name).collect(Collectors.toList());
          assertThat(interfaces).isEqualTo(Arrays.asList("ChildInterface1", "ChildInterface2"));
        });

        /**
         * This is the compile time interfaces type
         */
        it("has several implemented types", () -> {
          List<String> interfaces = context().typeInstance().hierarchy().implementedTypes().map(Named::name).collect(Collectors.toList());
          assertThat(interfaces).isEqualTo(Arrays.asList("ChildInterface1", "ChildInterface2"));
        });

        /**
         * This is the runtime super types
         */
        it("has supertypes ( superclass and interfaces)", () -> {
          List<String> supertypeNames = context().typeInstance().runtime().hierarchy().supertypes().map(Named::name).collect(Collectors.toList());
          assertThat(supertypeNames).isEqualTo(Arrays.asList("ParentClass", "ChildInterface1", "ChildInterface2"));
        });
        /**
         * This is the compile time super types
         */
        it("has supertypes ( superclass and interfaces)", () -> {
          List<String> supertypeNames = context().typeInstance().hierarchy().supertypes().map(Named::name).collect(Collectors.toList());
          assertThat(supertypeNames).isEqualTo(Arrays.asList("ParentClass", "ChildInterface1", "ChildInterface2"));
        });
      });


      describe("generics", () -> {
        it("has type parameters", () -> {
          List<String> parameterNames = context().typeInstance().generic().parameters()
            .map((typeParameter) -> typeParameter.name())
            .collect(Collectors.toList());
          assertThat(parameterNames).isEqualTo(Arrays.asList("C"));
        });

        it("has type arguments", () -> {
          List<String> parameterNames = context().typeInstance().generic().arguments()
            .map((typeParameter) -> typeParameter.name())
            .collect(Collectors.toList());
          assertThat(parameterNames).isEqualTo(Arrays.asList());
        });

        /**
         * The superclass is its un-parameterized supertype (the one that's used on runtime)
         */
        it("has correct type arguments for its superclass", () -> {
          List<String> parameterNames = context().typeInstance().runtime().hierarchy()
            .superclass().get()
            .generic().arguments()
            .map((typeParameter) -> typeParameter.name())
            .collect(Collectors.toList());
          assertThat(parameterNames).isEqualTo(Arrays.asList());
        });

        /**
         * The extended type is its parameterized supertype (the one that's is used on compile time)
         */
        it("has correct type arguments for its extended type", () -> {
          List<String> parameterNames = context().typeInstance()
            .hierarchy().extendedType()
            .get()
            .generic().arguments()
            .map((typeParameter) -> typeParameter.name())
            .collect(Collectors.toList());
          assertThat(parameterNames).isEqualTo(Arrays.asList("C", "Integer"));
        });

      });

      describe("for arrays", () -> {
        it("has a component type", () -> {
          context().typeInstance(this::getStringArrayType);

          final Unary<TypeInstance> componentType = context().typeInstance().componentType();
          assertThat(componentType.get().name()).isEqualTo("String");
        });
      });

      it("can be accessed from its type instance", () -> {
        final Class<ChildClass> reflectionType = context().typeInstance().reflectedAs(Class.class).get();
        assertThat(reflectionType).isEqualTo(ChildClass.class);
      });

    });
  }

  private TypeInstance getStringArrayType() {
    return Diamond.of(String[].class);
  }


}
