package ar.com.kfgodel.diamond;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.testobjects.lineage.ChildClass;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

        describe("a class lineage", ()->{

            beforeEach(()->{
                context().lineage(()-> Diamond.of(ChildClass.class).lineage());
            });

            it("starts from its creator class as the lowest descendant", ()->{
                assertThat(context().lineage().lowestDescendant().name())
                        .isEqualTo("ChildClass");
            });

            it("ends with Object as the highest ancestor", ()->{
                assertThat(context().lineage().highestAncestor().name())
                        .isEqualTo("Object");
            });

            it("contains all the classes in between", ()->{
                Stream<TypeInstance> lineageMembers = context().lineage().allMembers();
                List<String> memberNames = lineageMembers.map((member) -> member.name()).collect(Collectors.toList());
                assertThat(memberNames)
                        .isEqualTo(Arrays.asList("ChildClass", "ParentClass", "GrandParentClass", "Object"));
            });

            it("can answer the ancestor of a member", ()->{
                TypeInstance childType = context().lineage().lowestDescendant();
                TypeInstance parentType = context().lineage().ancestorOf(childType).get();
                Optional<TypeInstance> ancestor = context().lineage().ancestorOf(parentType);
                assertThat(ancestor.get().name()).isEqualTo("GrandParentClass");
            });


            it("can answer the descendant of a member", ()->{
                TypeInstance childType = context().lineage().lowestDescendant();
                TypeInstance parentType = context().lineage().ancestorOf(childType).get();
                Optional<TypeInstance> descendant = context().lineage().descendantOf(parentType);
                assertThat(descendant.get().name()).isEqualTo("ChildClass");
            });

            it("does not include Object for primitive types", ()->{
                Stream<TypeInstance> lineageMembers = Diamond.of(int.class).lineage().allMembers();
                List<String> memberNames = lineageMembers.map((member) -> member.name()).collect(Collectors.toList());
                assertThat(memberNames)
                        .isEqualTo(Arrays.asList("int"));
            });

            describe("generic arguments", ()->{
                it("start from the lowest descendant", ()->{
                    List<String> argumentNames = context().lineage().lowestDescendant().typeArguments().map((arg) -> arg.name()).collect(Collectors.toList());
                    assertThat(argumentNames).isEqualTo(Arrays.asList());
                });
                it("bubble up to its parent", ()->{
                    TypeInstance childType = context().lineage().lowestDescendant();
                    List<String> argumentNames = context().lineage().ancestorOf(childType).get()
                            .typeArguments().map((arg) -> arg.name()).collect(Collectors.toList());
                    assertThat(argumentNames).isEqualTo(Arrays.asList("C", "Integer"));
                });
                it("grand parents, and so on", ()->{
                    TypeInstance childType = context().lineage().lowestDescendant();
                    TypeInstance parentType = context().lineage().ancestorOf(childType).get();
                    List<String> argumentNames = context().lineage().ancestorOf(parentType).get()
                            .typeArguments().map((arg) -> arg.name()).collect(Collectors.toList());
                    assertThat(argumentNames).isEqualTo(Arrays.asList("Integer"));
                });
            });

        });

    }
}
