package ar.com.kfgodel.diamond.unit.objects;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.BoundField;
import ar.com.kfgodel.diamond.api.methods.BoundMethod;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.objects.MetaObject;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import ar.com.kfgodel.nary.api.Nary;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of a meta object
 * <p>
 * Created by kfgodel on 17/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class MetaObjectTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a meta object", () -> {

      it("is a meta-level description of an object", () -> {
        MetaObject metaObject = Diamond.metaObjects().from(new PublicMembersTestObject());

        assertThat(metaObject).isNotNull();
      });

      it("has the object as instance", () -> {
        MetaObject metaObject = Diamond.metaObjects().from("hi");
        assertThat(metaObject.instance()).isEqualTo("hi");
      });

      it("has the same hashcode as the object", () -> {
        PublicMembersTestObject instance = new PublicMembersTestObject();

        MetaObject metaObject = Diamond.metaObjects().from(instance);

        assertThat(metaObject.hashCode()).isEqualTo(instance.hashCode());
      });

      it("has the same equality criteria as the object", () -> {
        PublicMembersTestObject instance = new PublicMembersTestObject();

        MetaObject metaObject = Diamond.metaObjects().from(instance);

        assertThat(metaObject.equals(instance)).isTrue();
        // But the converse is not true
        assertThat(instance.equals(metaObject)).isFalse();
      });

      it("allows access to all the methods bound to the object", () -> {
        PublicMembersTestObject instance = new PublicMembersTestObject(45);
        MetaObject metaObject = Diamond.metaObjects().from(instance);

        Nary<BoundMethod> allMethods = metaObject.methods().all();
        List<String> allMethodNames = allMethods.map(Named::name).collect(Collectors.toList());

        assertThat(allMethodNames).contains("method", "finalize", "wait", "wait", "wait", "equals", "toString", "hashCode", "getClass", "clone", "registerNatives", "notify", "notifyAll");
      });

      it("allows access to all the fields bound to the object", () -> {
        PublicMembersTestObject instance = new PublicMembersTestObject(22);
        MetaObject metaObject = Diamond.metaObjects().from(instance);

        Nary<BoundField> allFields = metaObject.fields().all();
        List<String> allFieldNames = allFields.map(Named::name).collect(Collectors.toList());

        assertThat(allFieldNames).contains("field");
      });

      it("allows access to the object type", () -> {
        PublicMembersTestObject instance = new PublicMembersTestObject();
        MetaObject metaObject = Diamond.metaObjects().from(instance);

        TypeInstance type = metaObject.type();

        assertThat(type.name()).isEqualTo("PublicMembersTestObject");
      });

    });

  }
}