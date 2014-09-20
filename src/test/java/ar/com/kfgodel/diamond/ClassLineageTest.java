package ar.com.kfgodel.diamond;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.testobjects.lineage.ChildClass;
import ar.com.kfgodel.diamond.testobjects.lineage.GrandParentClass;
import ar.com.kfgodel.diamond.testobjects.lineage.ParentClass;
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
public class ClassLineageTest extends JavaSpec<DiamondTestContext> {
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
                Stream<ClassInstance> lineageMembers = context().lineage().allMembers();
                List<String> memberNames = lineageMembers.map((member) -> member.name()).collect(Collectors.toList());
                assertThat(memberNames)
                        .isEqualTo(Arrays.asList("ChildClass", "ParentClass", "GrandParentClass", "Object"));
            });

            it("can answer the ancestor of a member", ()->{
                Optional<ClassInstance> ancestor = context().lineage().ancestorOf(Diamond.of(ParentClass.class));
                assertThat(ancestor.get().name()).isEqualTo("GrandParentClass");
            });


            it("can answer the descendant of a member", ()->{
                Optional<ClassInstance> descendant = context().lineage().descendantOf(Diamond.of(ParentClass.class));
                assertThat(descendant.get().name()).isEqualTo("ChildClass");
            });

            it("does not include Object for primitive types", ()->{
                Stream<ClassInstance> lineageMembers = Diamond.of(int.class).lineage().allMembers();
                List<String> memberNames = lineageMembers.map((member) -> member.name()).collect(Collectors.toList());
                assertThat(memberNames)
                        .isEqualTo(Arrays.asList("int"));
            });


            it("can answer the actual value for a generic type parameter of a member", ()->{
                context().lineage().getActualTypeArgumentsFor(GrandParentClass.class);
            });

        });

    }
}
