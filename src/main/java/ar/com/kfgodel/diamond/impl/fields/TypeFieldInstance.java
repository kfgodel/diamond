package ar.com.kfgodel.diamond.impl.fields;

import ar.com.kfgodel.diamond.api.fields.BoundField;
import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.fields.bound.BoundFieldInstance;
import ar.com.kfgodel.diamond.impl.fields.declaration.FieldDeclaration;
import ar.com.kfgodel.diamond.impl.fields.equality.FieldEquality;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;
import ar.com.kfgodel.diamond.impl.strings.DebugPrinter;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents a class field instance for a type
 * Created by kfgodel on 12/10/14.
 */
public class TypeFieldInstance extends TypeMemberSupport implements TypeField {

  private Supplier<TypeInstance> fieldType;
  private Supplier<BiConsumer<Object, Object>> setter;
  private Supplier<Function<Object, Object>> getter;
  private Supplier<Unary<Field>> nativeField;
  private Function<TypeField, Object> identityToken;


  @Override
  public TypeInstance type() {
    return fieldType.get();
  }

  @Override
  public <R> R getValueFrom(Object instance) {
    return (R) getter.get().apply(instance);
  }

  @Override
  public void setValueOn(Object instance, Object value) {
    setter.get().accept(instance, value);
  }

  @Override
  public Object get() {
    return getValueFrom(null);
  }

  @Override
  public void accept(Object argument) {
    setValueOn(null, argument);
  }

  @Override
  public void accept(Object instance, Object value) {
    setValueOn(instance, value);
  }

  @Override
  public Object apply(Object instance) {
    return getValueFrom(instance);
  }

  @Override
  public Object invoke(Object... arguments) {
    return asLambda().invoke(arguments);
  }


  public static TypeFieldInstance create(FieldDescription description) {
    TypeFieldInstance field = new TypeFieldInstance();
    field.initialize(description);
    field.fieldType = description.getType();
    field.setter = description.getSetter();
    field.getter = description.getGetter();
    field.nativeField = description.getNativeField();
    field.identityToken = description.getIdentityToken();
    return field;
  }

  @Override
  public String declaration() {
    return FieldDeclaration.create(this).asString();
  }

  @Override
  public Nary<TypeInstance> parameterTypes() {
    return Nary.empty();
  }

  @Override
  public TypeInstance returnType() {
    return type();
  }

  @Override
  public BoundField bindTo(Object object) {
    return BoundFieldInstance.create(this, object);
  }

  @Override
  public Unary<Field> nativeType() {
    return nativeField.get();
  }

  @Override
  public Object getIdentityToken() {
    return identityToken.apply(this);
  }

  @Override
  public boolean equals(Object obj) {
    return FieldEquality.INSTANCE.areEquals(this, obj);
  }

  @Override
  public String toString() {
    return DebugPrinter.print(this);
  }
}
