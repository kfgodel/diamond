package ar.com.kfgodel.diamond.impl.constructors;

import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.members.call.BehaviorCall;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.constructors.declaration.ConstructorDeclaration;
import ar.com.kfgodel.diamond.impl.constructors.equality.ConstructorEquality;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;
import ar.com.kfgodel.diamond.impl.members.call.BehaviorCallInstance;
import ar.com.kfgodel.diamond.impl.strings.DebugPrinter;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.reflect.Constructor;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents an instance of type constructor
 * Created by kfgodel on 15/10/14.
 */
public class TypeConstructorInstance extends TypeMemberSupport implements TypeConstructor {

  private Supplier<Unary<Constructor>> nativeConstructor;
  private Function<TypeConstructor, Object> identityToken;

  @Override
  public boolean equals(Object obj) {
    return ConstructorEquality.INSTANCE.areEquals(this, obj);
  }

  @Override
  public Object getIdentityToken() {
    return identityToken.apply(this);
  }

  @Override
  public Object invoke(Object... arguments) {
    return asLambda().invoke(arguments);
  }

  @Override
  public Object get() {
    return asLambda().get();
  }

  @Override
  public Object apply(Object argument) {
    return asLambda().apply(argument);
  }


  public static TypeConstructor create(ConstructorDescription description) {
    TypeConstructorInstance constructor = new TypeConstructorInstance();
    constructor.initialize(description);
    constructor.nativeConstructor = description.getNativeConstructor();
    constructor.identityToken = description.getIdentityToken();
    return constructor;
  }

  @Override
  public String declaration() {
    return ConstructorDeclaration.create(this).asString();
  }

  @Override
  public TypeInstance returnType() {
    return declaringType();
  }

  @Override
  public BehaviorCall withArguments(Object... arguments) {
    return BehaviorCallInstance.create(this, arguments);
  }

  @Override
  public Unary<Constructor> nativeType() {
    return nativeConstructor.get();
  }

  @Override
  public boolean isInstanceMember() {
    // Constructors are always class members (don't require an instance)
    return false;
  }

  @Override
  public Object invokeOn(Object instance, Object... arguments) {
    // The instance has no relevance for a constructor
    return invoke(arguments);
  }

  @Override
  public String toString() {
    return DebugPrinter.print(this);
  }
}
