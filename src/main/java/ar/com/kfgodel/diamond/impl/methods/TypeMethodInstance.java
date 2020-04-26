package ar.com.kfgodel.diamond.impl.methods;

import ar.com.kfgodel.diamond.api.members.call.BehaviorCall;
import ar.com.kfgodel.diamond.api.methods.BoundMethod;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;
import ar.com.kfgodel.diamond.impl.members.call.BehaviorCallInstance;
import ar.com.kfgodel.diamond.impl.methods.bound.BoundMethodInstance;
import ar.com.kfgodel.diamond.impl.methods.declaration.MethodDeclaration;
import ar.com.kfgodel.diamond.impl.methods.equality.MethodEquality;
import ar.com.kfgodel.diamond.impl.natives.invokables.InstanceArguments;
import ar.com.kfgodel.diamond.impl.strings.DebugPrinter;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents a method that belongs to a type
 * Created by kfgodel on 18/09/14.
 */
public class TypeMethodInstance extends TypeMemberSupport implements TypeMethod {

  private Supplier<TypeInstance> returnType;
  private Supplier<Unary<Object>> defaultValue;
  private Supplier<Unary<Method>> nativeMethod;
  private Function<TypeMethod, Object> identityToken;


  @Override
  public TypeInstance returnType() {
    return returnType.get();
  }

  @Override
  public Object invokeOn(Object instance, Object... arguments) {
    Object[] invokableArguments = InstanceArguments.join(instance, arguments);
    return this.invoke(invokableArguments);
  }

  @Override
  public Object invoke(Object... arguments) {
    return asLambda().invoke(arguments);
  }

  @Override
  public void run() {
    asLambda().run();
  }

  @Override
  public Object get() {
    return asLambda().get();
  }

  @Override
  public void accept(Object argumentOrInstance) {
    asLambda().accept(argumentOrInstance);
  }

  @Override
  public Object apply(Object argumentOrInstance) {
    return asLambda().apply(argumentOrInstance);
  }

  @Override
  public void accept(Object argumentOrInstance, Object extraArgument) {
    asLambda().accept(argumentOrInstance, extraArgument);
  }

  @Override
  public boolean test(Object argumentOrInstance) {
    return asLambda().test(argumentOrInstance);
  }

  @Override
  public boolean equals(Object obj) {
    return MethodEquality.INSTANCE.areEquals(this, obj);
  }

  @Override
  public Object getIdentityToken() {
    return identityToken.apply(this);
  }

  public static TypeMethodInstance create(MethodDescription description) {
    TypeMethodInstance method = new TypeMethodInstance();
    method.initialize(description);
    method.nativeMethod = description.getNativeMethod();
    method.returnType = description.getReturnType();
    method.defaultValue = description.getDefaultValue();
    method.identityToken = description.getIdentityToken();
    return method;
  }

  @Override
  public String declaration() {
    return MethodDeclaration.create(this).asString();
  }

  @Override
  public Unary<Object> defaultValue() {
    return defaultValue.get();
  }

  @Override
  public BoundMethod bindTo(Object instance) {
    return BoundMethodInstance.create(this, instance);
  }

  @Override
  public BehaviorCall withArguments(Object... arguments) {
    return BehaviorCallInstance.create(this, arguments);
  }

  @Override
  public Unary<Method> nativeType() {
    return nativeMethod.get();
  }

  @Override
  public String toString() {
    return DebugPrinter.print(this);
  }
}
