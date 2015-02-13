package ar.com.kfgodel.diamond.unit.lineage;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass;
import ar.com.kfgodel.nary.api.Nary;
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
public class TypeLineageTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {

        describe("type lineage", ()->{

            context().lineage(()-> Diamond.of(ChildClass.class).inheritance().typeLineage());

            it("starts from its creator class as the lowest descendant", ()->{
                assertThat(context().lineage().lowestDescendant().name())
                        .isEqualTo("ChildClass");
            });

            it("ends with Object as the highest ancestor", ()->{
                assertThat(context().lineage().highestAncestor().name())
                        .isEqualTo("Object");
            });

            it("contains all the types in between", ()->{
                Stream<TypeInstance> lineageMembers = context().lineage().allMembers();
                List<String> memberNames = lineageMembers.map((member) -> member.name()).collect(Collectors.toList());
                assertThat(memberNames)
                        .isEqualTo(Arrays.asList("ChildClass", "ParentClass", "GrandParentClass", "Object"));
            });

            it("can answer the ancestor of a member", ()->{
                TypeInstance childType = context().lineage().lowestDescendant();
                TypeInstance parentType = context().lineage().ancestorOf(childType).get();
                Nary<TypeInstance> ancestor = context().lineage().ancestorOf(parentType);
                assertThat(ancestor.get().name()).isEqualTo("GrandParentClass");
            });


            it("can answer the descendant of a member", ()->{
                TypeInstance childType = context().lineage().lowestDescendant();
                TypeInstance parentType = context().lineage().ancestorOf(childType).get();
                Nary<TypeInstance> descendant = context().lineage().descendantOf(parentType);
                assertThat(descendant.get().name()).isEqualTo("ChildClass");
            });

            it("does not include Object for primitive types", ()->{
                Stream<TypeInstance> lineageMembers = Diamond.of(int.class).inheritance().typeLineage().allMembers();
                List<String> memberNames = lineageMembers.map((member) -> member.name()).collect(Collectors.toList());
                assertThat(memberNames)
                        .isEqualTo(Arrays.asList("int"));
            });

            it("can answer all the inherited interfaces of the lowest member",()->{
                List<String> interfaceNames = context().lineage().inheritedInterfaces().map(Named::name).collect(Collectors.toList());

                assertThat(interfaceNames).isEqualTo(Arrays.asList("ChildInterface1", "ParentInterface1", "ChildInterface2", "Serializable", "ParentInterface2", "GrandParentInterface1"));
            });

            describe("generic arguments", ()->{
                it("start from the lowest descendant", ()->{
                    List<String> argumentNames = context().lineage().lowestDescendant().generics().arguments().map(Named::name).collect(Collectors.toList());
                    assertThat(argumentNames).isEqualTo(Arrays.asList());
                });
                it("bubble up to its parent", ()->{
                    TypeInstance childType = context().lineage().lowestDescendant();
                    List<String> argumentNames = context().lineage().ancestorOf(childType).get()
                            .generics().arguments().map((arg) -> arg.name()).collect(Collectors.toList());
                    assertThat(argumentNames).isEqualTo(Arrays.asList("C", "Integer"));
                });
                it("grand parents, and so on", ()->{
                    TypeInstance childType = context().lineage().lowestDescendant();
                    TypeInstance parentType = context().lineage().ancestorOf(childType).get();
                    List<String> argumentNames = context().lineage().ancestorOf(parentType).get()
                            .generics().arguments().map((arg) -> arg.name()).collect(Collectors.toList());
                    assertThat(argumentNames).isEqualTo(Arrays.asList("Integer"));
                });
            });

            describe("all related types", () -> {

                it("includes all the types related to the lineage (hiearchy tree)", () -> {
                    List<String> allTypeNames = context().lineage().allRelatedTypes().map(TypeInstance::declaration).collect(Collectors.toList());
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
                    TypeInheritance inheritance = Diamond.types().from(new ReferenceOf<List<String>>() {
                    }.getReferencedAnnotatedType()).inheritance();

                    List<String> allTypeNames = inheritance.classLineage().allRelatedTypes().map(TypeInstance::declaration).collect(Collectors.toList());
                    assertThat(allTypeNames)
                            .isEqualTo(Arrays.asList("java.util.List<java.lang.String>", "java.util.List",
                                    "java.util.Collection<java.lang.String>", "java.util.Collection",
                                    "java.lang.Iterable<java.lang.String>", "java.lang.Iterable"));
                });


            });

        });

        describe("class lineage", () -> {

            beforeEach(() -> {
                context().lineage(() -> Diamond.of(ChildClass.class).inheritance().classLineage());
            });

            it("doesn't have type arguments for any member", () -> {
                TypeInstance childType = context().lineage().lowestDescendant();
                assertThat(childType.generics().arguments().count()).isEqualTo(0);

                TypeInstance parentType = context().lineage().ancestorOf(childType).get();
                assertThat(parentType.generics().arguments().count()).isEqualTo(0);

                TypeInstance grandParentType = context().lineage().ancestorOf(parentType).get();
                assertThat(grandParentType.generics().arguments().count()).isEqualTo(0);
            });
        });

    }
}
