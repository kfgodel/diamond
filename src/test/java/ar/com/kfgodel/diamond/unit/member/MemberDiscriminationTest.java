package ar.com.kfgodel.diamond.unit.member;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.members.predicates.IsAnnotated;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.MemberAnnotationTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.StaticMembersTestObject;
import ar.com.kfgodel.nary.api.Nary;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies that different members are discriminable by predicates
 * Created by kfgodel on 14/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class MemberDiscriminationTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a constructor", () -> {
            it("is a class member",()->{
                TypeConstructor constructor = Diamond.of(PublicMembersTestObject.class).constructors().niladic().get();
                assertThat(constructor.isInstanceMember()).isFalse();
            });
            it("can be filtered by annotation",()->{
                Optional<TypeConstructor> annotatedConstructor = Diamond.of(MemberAnnotationTestObject.class).constructors().all().filter(IsAnnotated.with(TestAnnotation1.class)).findFirst();
                assertThat(annotatedConstructor.isPresent()).isTrue();
            });   
        });

        describe("a method", () -> {
            it("is a class member if static",()->{
                TypeMethod method = Diamond.of(StaticMembersTestObject.class).methods().named("method").get();
                assertThat(method.isInstanceMember()).isFalse();
            }); 
            
            it("is an instance member if non static",()->{
                TypeMethod method = Diamond.of(PublicMembersTestObject.class).methods().named("method").get();
                assertThat(method.isInstanceMember()).isTrue();
            });
            it("can be filtered by annotation",()->{
                Optional<TypeMethod> annotatedMethod = Diamond.of(MemberAnnotationTestObject.class).methods().all().filter(IsAnnotated.with(TestAnnotation1.class)).findFirst();
                assertThat(annotatedMethod.isPresent()).isTrue();
            });
        });

        describe("a field", () -> {
            it("is a class member if static",()->{
                TypeField method = Diamond.of(StaticMembersTestObject.class).fields().named("field").get();
                assertThat(method.isInstanceMember()).isFalse();
            });

            it("is an instance member if non static",()->{
                TypeField method = Diamond.of(PublicMembersTestObject.class).fields().named("field").get();
                assertThat(method.isInstanceMember()).isTrue();
            });
            it("can be filtered by annotation",()->{
                Optional<TypeField> annotatedField = Diamond.of(MemberAnnotationTestObject.class).fields().all().filter(IsAnnotated.with(TestAnnotation1.class)).findFirst();
                assertThat(annotatedField.isPresent()).isTrue();
            });
        });

        describe("all members", () -> {

            it("can be accessed together",()->{
                Nary<TypeMember> allMembers = Diamond.of(PublicMembersTestObject.class).members();

                List<String> memberNames = allMembers.map(Named::name).collect(Collectors.toList());

                assertThat(memberNames).contains("field","method","ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject");
            });
            
            it("discriminated by type of member",()->{
                Nary<TypeMember> allMembers = Diamond.of(PublicMembersTestObject.class).members();

                List<String> memberNames = allMembers.filter((member)-> !TypeConstructor.class.isInstance(member) ).map(Named::name).collect(Collectors.toList());

                assertThat(memberNames).doesNotContain("ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject");

            });   

        });


    }
}