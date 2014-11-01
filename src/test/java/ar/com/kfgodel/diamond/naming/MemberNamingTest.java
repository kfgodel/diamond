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
                    // For some reason type annotation is shared as field annotation
                    assertThat(context().field().declaration())
                            .isEqualTo("@ar.com.kfgodel.diamond.testobjects.annotations.FieldTestAnnotation() @ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation2() public @ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation2() java.util.List<java.lang.String> stringList /* MemberNamingTestObject */");
                });
                
                it("toString is equal to the declaration",()->{
                    assertThat(context().field().toString()).isEqualTo(context().field().declaration());
                });   
            });


            describe("for methods", () -> {

                context().method(() -> Diamond.of(MemberNamingTestObject.class).methods().existingNamed("methodWithArgs"));

                it("is just the name without arguments or return type", () -> {
                    assertThat(context().method().name()).isEqualTo("methodWithArgs");
                });

                it("has a complete declaration name",()->{
                    // For some reason return type annotation is shared as method annotation
                    assertThat(context().method().declaration())
                            .isEqualTo("@ar.com.kfgodel.diamond.testobjects.annotations.MethodTestAnnotation() @ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation3() public <S extends java.lang.Object> @ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation3() int methodWithArgs(java.lang.String, S extends java.lang.Object) /* MemberNamingTestObject */");
                });

                it("toString is equal to the declaration",()->{
                    assertThat(context().method().toString()).isEqualTo(context().method().declaration());
                });

            });

            describe("for constructors", () -> {

                context().constructor(() -> Diamond.of(MemberNamingTestObject.class).constructors().existingDeclaredFor(Diamond.of(Integer.class)));
                
                it("is the complete declaring type name",()->{
                    assertThat(context().constructor().name()).isEqualTo("ar.com.kfgodel.diamond.testobjects.MemberNamingTestObject");
                });   

                it("only has a declaration name", () -> {
                    assertThat(context().constructor().declaration())
                            .isEqualTo("@ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation1() public <R extends java.lang.Object> MemberNamingTestObject(java.lang.Integer)");
                });

                it("toString is equal to the declaration",()->{
                    assertThat(context().constructor().toString()).isEqualTo(context().constructor().declaration());
                });

            });


        });


    }
}
