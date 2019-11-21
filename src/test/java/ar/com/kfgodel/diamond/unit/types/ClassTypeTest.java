package ar.com.kfgodel.diamond.unit.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass;
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
          TypeLineage typeLineage = context().typeInstance().inheritance().typeLineage();
          assertThat(typeLineage.highestAncestor().name())
            .isEqualTo("Object");
        });

        /**
         * This is the runtime parent type
         */
        it("has a super class", () -> {
          String superClassName = context().typeInstance().inheritance().superclass().map(TypeInstance::name).get();
          assertThat(superClassName).isEqualTo("ParentClass");
        });

        /**
         * This is the compile time parent type
         */
        it("has an extended type", () -> {
          String extendedTypeName = context().typeInstance().inheritance().extendedType().map(Named::name).get();
          assertThat(extendedTypeName).isEqualTo("ParentClass");
        });

        /**
         * This is the runtime interface types
         */
        it("has several interfaces", () -> {
          List<String> interfaces = context().typeInstance().inheritance().interfaces().map(Named::name).collect(Collectors.toList());
          assertThat(interfaces).isEqualTo(Arrays.asList("ChildInterface1", "ChildInterface2"));
        });

        /**
         * This is the compile time interfaces type
         */
        it("has several implemented types", () -> {
          List<String> interfaces = context().typeInstance().inheritance().implementedTypes().map(Named::name).collect(Collectors.toList());
          assertThat(interfaces).isEqualTo(Arrays.asList("ChildInterface1", "ChildInterface2"));
        });

        it("has supertypes ( superclass and interfaces)", () -> {
          List<String> supertypeNames = context().typeInstance().inheritance().supertypes().map(Named::name).collect(Collectors.toList());
          assertThat(supertypeNames).isEqualTo(Arrays.asList("ParentClass", "ChildInterface1", "ChildInterface2"));
        });
      });


      describe("generics", () -> {
        it("has type parameters", () -> {
          List<String> parameterNames = context().typeInstance().generics().parameters()
            .map((typeParameter) -> typeParameter.name())
            .collect(Collectors.toList());
          assertThat(parameterNames).isEqualTo(Arrays.asList("C"));
        });

        it("has type arguments", () -> {
          List<String> parameterNames = context().typeInstance().generics().arguments()
            .map((typeParameter) -> typeParameter.name())
            .collect(Collectors.toList());
          assertThat(parameterNames).isEqualTo(Arrays.asList());
        });

        /**
         * The superclass is its un-parameterized supertype (the one that's used on runtime)
         */
        it("has correct type arguments for its superclass", () -> {
          List<String> parameterNames = context().typeInstance().inheritance().superclass().get().generics().arguments()
            .map((typeParameter) -> typeParameter.name())
            .collect(Collectors.toList());
          assertThat(parameterNames).isEqualTo(Arrays.asList());
        });

        /**
         * The extended type is its parameterized supertype (the one that's is used on compile time)
         */
        it("has correct type arguments for its extended type", () -> {
          List<String> parameterNames = context().typeInstance().inheritance().extendedType().get().generics().arguments()
            .map((typeParameter) -> typeParameter.name())
            .collect(Collectors.toList());
          assertThat(parameterNames).isEqualTo(Arrays.asList("C", "Integer"));
        });

      });

      describe("for arrays", () -> {
        it("has a component type", () -> {
          context().typeInstance(() -> getStringArrayType());
          assertThat(context().typeInstance().componentType().get().name()).isEqualTo("String");
        });
      });

    });
  }

  private TypeInstance getStringArrayType() {
    return Diamond.types().from(new ReferenceOf<String[]>() {
    }.getReferencedAnnotatedType());
  }


}
