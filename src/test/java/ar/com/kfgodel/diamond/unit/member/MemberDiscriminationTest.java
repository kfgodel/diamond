package ar.com.kfgodel.diamond.unit.member;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.StaticMembersTestObject;
import org.junit.runner.RunWith;

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
        });


    }
}