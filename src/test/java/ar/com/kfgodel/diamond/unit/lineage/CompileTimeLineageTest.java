package ar.com.kfgodel.diamond.unit.lineage;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.compile.CompileTimeHierarchy;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.interfaces.GrandParentInterface1;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass;
import ar.com.kfgodel.nary.api.Nary;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests lineage operations
 * Created by kfgodel on 19/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class CompileTimeLineageTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {

    describe("a type's compile time lineage", () -> {

      context().lineage(() -> Diamond.of(ChildClass.class).hierarchy().lineage());

      it("starts from its creator class as the lowest descendant", () -> {
        assertThat(context().lineage().lowestDescendant().name())
          .isEqualTo("ChildClass");
      });

      it("ends with Object as the highest ancestor", () -> {
        assertThat(context().lineage().highestAncestor().name())
          .isEqualTo("Object");
      });

      it("contains all the types in between", () -> {
        Stream<TypeInstance> lineageMembers = context().lineage().allExtendedTypes();
        List<String> memberNames = lineageMembers.map((member) -> member.name()).collect(Collectors.toList());
        assertThat(memberNames)
          .isEqualTo(Arrays.asList("ChildClass", "ParentClass", "GrandParentClass", "Object"));
      });

      it("can answer the ancestor of a member", () -> {
        TypeInstance childType = context().lineage().lowestDescendant();
        TypeInstance parentType = context().lineage().ancestorOf(childType).unique().get();
        Nary<TypeInstance> ancestor = context().lineage().ancestorOf(parentType);
        assertThat(ancestor.unique().get().name()).isEqualTo("GrandParentClass");
      });


      it("can answer the descendant of a member", () -> {
        TypeInstance childType = context().lineage().lowestDescendant();
        TypeInstance parentType = context().lineage().ancestorOf(childType).unique().get();
        Nary<TypeInstance> descendant = context().lineage().descendantOf(parentType);
        assertThat(descendant.unique().get().name()).isEqualTo("ChildClass");
      });

      it("does not include Object for primitive types", () -> {
        Stream<TypeInstance> lineageMembers = Diamond.of(int.class).hierarchy().lineage().allExtendedTypes();
        List<String> memberNames = lineageMembers.map((member) -> member.name()).collect(Collectors.toList());
        assertThat(memberNames)
          .isEqualTo(Arrays.asList("int"));
      });

      describe("generic arguments", () -> {
        it("start from the lowest descendant", () -> {
          List<String> argumentNames = context().lineage().lowestDescendant().generics().arguments().map(Named::name).collect(Collectors.toList());
          assertThat(argumentNames).isEqualTo(Arrays.asList());
        });
        it("bubble up to its parent", () -> {
          TypeInstance childType = context().lineage().lowestDescendant();
          List<String> argumentNames = context().lineage().ancestorOf(childType).unique().get()
            .generics().arguments().map((arg) -> arg.name()).collect(Collectors.toList());
          assertThat(argumentNames).isEqualTo(Arrays.asList("C", "Integer"));
        });
        it("grand parents, and so on", () -> {
          TypeInstance childType = context().lineage().lowestDescendant();
          TypeInstance parentType = context().lineage().ancestorOf(childType).unique().get();
          List<String> argumentNames = context().lineage().ancestorOf(parentType).unique().get()
            .generics().arguments().map((arg) -> arg.name()).collect(Collectors.toList());
          assertThat(argumentNames).isEqualTo(Arrays.asList("Integer"));
        });
      });

      describe("all related types", () -> {

        it("includes all the types related to the lineage (hiearchy tree)", () -> {
          List<String> allTypeNames = context().lineage().allRelatedTypes()
            .map(TypeInstance::declaration)
            .collect(Collectors.toList());
          assertThat(allTypeNames)
            .isEqualTo(Arrays.asList(
              "ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass",
              "ar.com.kfgodel.diamond.unit.testobjects.lineage.ParentClass<C extends java.lang.Object, java.lang.Integer>",
              "ar.com.kfgodel.diamond.unit.testobjects.lineage.ParentClass",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ChildInterface1<java.lang.Integer>",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ChildInterface1",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ChildInterface2<java.lang.String>",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ChildInterface2",
              "ar.com.kfgodel.diamond.unit.testobjects.lineage.GrandParentClass<java.lang.Integer>",
              "ar.com.kfgodel.diamond.unit.testobjects.lineage.GrandParentClass",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ParentInterface2<java.lang.Integer, java.lang.Integer>",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ParentInterface2",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ParentInterface1<java.lang.Integer, java.lang.Integer>",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ParentInterface1",
              "java.io.Serializable",
              "java.lang.Object",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.GrandParentInterface1<java.lang.Integer>",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.GrandParentInterface1"));
        });

        it("includes parameterized and raw types", () -> {
          CompileTimeHierarchy inheritance = Diamond.types().from(new ReferenceOf<List<String>>() {
          }.getReferencedAnnotatedType()).hierarchy();

          List<String> allTypeNames = inheritance.lineage().allRelatedTypes()
            .map(TypeInstance::declaration)
            .collect(Collectors.toList());
          assertThat(allTypeNames)
            .isEqualTo(Arrays.asList(
              "java.util.List<java.lang.String>",
              "java.util.List",
              "java.util.Collection<java.lang.String>",
              "java.util.Collection",
              "java.lang.Iterable<java.lang.String>",
              "java.lang.Iterable"
            ));
        });

      });

      describe("all extended types", () -> {

        it("includes only types in the extension line from the lowest descendant", () -> {
          List<String> allTypeNames = context().lineage().allExtendedTypes()
            .map(TypeInstance::declaration)
            .collect(Collectors.toList());
          assertThat(allTypeNames)
            .isEqualTo(Arrays.asList(
              "ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass",
              "ar.com.kfgodel.diamond.unit.testobjects.lineage.ParentClass<C extends java.lang.Object, java.lang.Integer>",
              "ar.com.kfgodel.diamond.unit.testobjects.lineage.GrandParentClass<java.lang.Integer>",
              "java.lang.Object"
            ));
        });

        it("doesn't include implemented types", () -> {
          CompileTimeHierarchy inheritance = Diamond.types().from(new ReferenceOf<List<String>>() {
          }.getReferencedAnnotatedType()).hierarchy();

          List<String> allTypeNames = inheritance.lineage().allExtendedTypes()
            .map(TypeInstance::declaration)
            .collect(Collectors.toList());
          assertThat(allTypeNames)
            .isEqualTo(Arrays.asList(
              "java.util.List<java.lang.String>"
            ));
        });

      });

      describe("all implemented types", () -> {

        it("includes only interface types that are implemented in the lineage", () -> {
          List<String> allTypeNames = context().lineage().allImplementedTypes()
            .map(TypeInstance::declaration)
            .collect(Collectors.toList());
          assertThat(allTypeNames)
            .isEqualTo(Arrays.asList(
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ChildInterface1",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ParentInterface1",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ChildInterface2",
              "java.io.Serializable",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.ParentInterface2",
              "ar.com.kfgodel.diamond.unit.testobjects.interfaces.GrandParentInterface1"
            ));
        });

        it("doesn't include extended types", () -> {
          CompileTimeHierarchy inheritance = Diamond.types().from(new ReferenceOf<List<String>>() {
          }.getReferencedAnnotatedType()).hierarchy();

          List<String> allTypeNames = inheritance.lineage().allImplementedTypes()
            .map(TypeInstance::declaration)
            .collect(Collectors.toList());
          assertThat(allTypeNames)
            .isEqualTo(Arrays.asList(
              "java.util.Collection",
              "java.lang.Iterable"
            ));
        });

      });

      it("can answer the type arguments of any related type", () -> {
        List<String> argumentNames = context().lineage()
          .genericArgumentsOf(Diamond.of(GrandParentInterface1.class))
          .map((arg) -> arg.name()).collect(Collectors.toList());
        ;
        assertThat(argumentNames).isEqualTo(Arrays.asList("Integer"));
      });

    });

  }
}
