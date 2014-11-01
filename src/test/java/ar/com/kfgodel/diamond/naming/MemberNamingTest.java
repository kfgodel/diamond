package ar.com.kfgodel.diamond.naming;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.testobjects.MemberNamingTestObject;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the naming behavior for type members
 * Created by kfgodel on 01/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class MemberNamingTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("name", () -> {
            describe("for fields", () -> {

                context().field(()-> Diamond.of(MemberNamingTestObject.class).fields().existingNamed("stringList"));

                it("is just the plain name without type", () -> {
                    assertThat(context().field().name()).isEqualTo("stringList");
                });

                it("has a complete declaration name",()->{
                    assertThat(context().field().declaration()).isEqualTo("public List stringList /* MemberNamingTestObject */");
                });   
            });


            describe("for methods", () -> {

                context().method(() -> Diamond.of(MemberNamingTestObject.class).methods().existingNamed("methodWithArgs"));

                it("is just the name without arguments or return type", () -> {
                    assertThat(context().method().name()).isEqualTo("methodWithArgs");
                });

                it("has a complete declaration name",()->{
                    assertThat(context().method().declaration()).isEqualTo("public int methodWithArgs(String, Integer) /* MemberNamingTestObject */");
                });

            });

            describe("for constructors", () -> {

                context().constructor(() -> Diamond.of(MemberNamingTestObject.class).constructors().existingDeclaredFor(Diamond.of(Integer.class)));

                it("only has a declaration name", () -> {
                    assertThat(context().constructor().declaration()).isEqualTo("public MemberNamingTestObject(Integer)");
                });

            });


        });


    }
}
