package ar.com.kfgodel.diamond.impl.strings;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.BoundField;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.BoundMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * This type knows how to represent string versions of the diamond reflections
 * Created by kfgodel on 09/01/16.
 */
public class DebugPrinter {

  public static final String AT = " @ ";
  public static final String ATAT = " @@ ";
  public static final String COMA = ", ";
  public static final String AND = " & ";

  /**
   * Generates a string representation of the given package
   */
  public static String print(TypePackage aPackage) {
    return aPackage.name();
  }

  /**
   * Generates a string representation of the given type
   */
  public static String print(TypeInstance type) {
    return builtString((builder)->{
      builder.append(type.name());

      String argNames = type.generics().arguments()
        .map(TypeInstance::name)
        .collect(Collectors.joining(COMA));
      if(!argNames.isEmpty()){
        builder.append("<");
        builder.append(argNames);
        builder.append(">");
      }

      String upperBoundName = type.generics().bounds().upper()
        .map(TypeInstance::name)
        .collect(Collectors.joining(AND));
      if(!upperBoundName.isEmpty()){
        builder.append(" extends ");
        builder.append(upperBoundName);
      }

      String lowerBound = type.generics().bounds().lower()
        .map(TypeInstance::name)
        .collect(Collectors.joining(AND));
      if(!lowerBound.isEmpty()){
        builder.append(" super ");
        builder.append(lowerBound);
      }

      type.declaredPackage().ifPresent(typePackage -> {
        builder.append(AT);
        builder.append(typePackage.name());
      });
    });
  }

  /**
   * Generates a string representation for the given type member
   */
  public static String print(TypeField typeField) {
    return builtString((builder)->{
      builder.append(typeField.name());
      builder.append(AT);
      builder.append(typeField.declaringType().name());
    });
  }

  /**
   * Generates a string representation for the given type member
   */
  public static String print(TypeMethod typeMethod) {
    return builtString((builder)->{
      appendSignatureDataFrom(typeMethod, builder);
      builder.append(AT);
      builder.append(typeMethod.declaringType().name());
    });
  }

  /**
   * Generates a string representation of the given constructor
   */
  public static String print(TypeConstructor typeConstructor) {
    TypeInstance declaringType = typeConstructor.declaringType();
    return builtString((builder)->{
      builder.append(declaringType.name());
      appendParametersTo(builder, typeConstructor.parameterTypes());
      declaringType.declaredPackage().ifPresent(typePackage -> {
        builder.append(AT);
        builder.append(typePackage.name());
      });
    });
  }


  /**
   * Generates a string representation for the given bound member
   */
  public static String print(BoundField boundField) {
    return builtString((builder)->{
      builder.append(boundField.name());
      builder.append(ATAT);
      builder.append(boundField.instance());
    });
  }

  /**
   * Generates a string representation for the given bound member
   */
  public static String print(BoundMethod boundMethod) {
    return builtString((builder)->{
      appendSignatureDataFrom(boundMethod.typeMethod(), builder);
      builder.append(ATAT);
      builder.append(boundMethod.instance());
    });
  }

  /**
   * Generates a string representation of the given parameter
   */
  public static String print(ExecutableParameter parameter) {
    return builtString((builder)->{
      builder.append(parameter.declaredType().name());
      builder.append(" ");
      builder.append(parameter.name());
    });
  }



  /**
   * Adds to the given builder the name and argument types for the given method
   */
  private static void appendSignatureDataFrom(TypeMethod typeMethod, StringBuilder builder) {
    builder.append(typeMethod.name());
    appendParametersTo(builder, typeMethod.parameterTypes());
  }

  /**
   * Adds between parenthesis the parameter type names
   */
  private static void appendParametersTo(StringBuilder builder, Nary<TypeInstance> parameterTypes) {
    String paramNames = parameterTypes
      .map(TypeInstance::name)
      .collect(Collectors.joining(COMA));
    builder.append("(");
    builder.append(paramNames);
    builder.append(")");
  }

  /**
   * Generates a string representation with the operation defining the string contents
   */
  private static String builtString(Consumer<StringBuilder> operation) {
    StringBuilder builder = new StringBuilder();
    operation.accept(builder);
    return builder.toString();
  }
}
