package ar.com.kfgodel.diamond.impl.methods.declaration;

import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the source declaration for a field
 * Created by kfgodel on 01/11/14.
 */
public class MethodDeclaration {

  private TypeMethod method;

  public static MethodDeclaration create(TypeMethod method) {
    MethodDeclaration declaration = new MethodDeclaration();
    declaration.method = method;
    return declaration;
  }

  /**
   * Returns this declaration as a source string.<br>
   * Depending on the type relations with other types are included (component type, type arguments, etc)
   *
   * @return The declaration string for the involved type
   */
  public String asString() {
    StringBuilder builder = new StringBuilder();

    withAnnotationsSeparatedBy(" ", (separatedAnnotations) -> {
      builder.append(separatedAnnotations);
      builder.append(" ");
    });
    withModifiersSeparatedBy(" ", (separatedModifiers) -> {
      builder.append(separatedModifiers);
      builder.append(" ");
    });
    withTypeParametersSeparatedBy(", ", (separatedTypeParameters) -> {
      builder.append("<");
      builder.append(separatedTypeParameters);
      builder.append("> ");
    });
    builder.append(method.returnType().declaration());
    builder.append(" ");
    builder.append(method.name());
    builder.append("(");
    withParameterSeparatedBy(", ", builder::append);
    builder.append(") /* ");
    builder.append(method.declaringType().name());
    builder.append(" */");

    return builder.toString();
  }

  private void withAnnotationsSeparatedBy(String separator, Consumer<String> separatedAnnotationsConsumer) {
    transformAndJoin(method.annotations(), Object::toString, separator, separatedAnnotationsConsumer);
  }

  private void withModifiersSeparatedBy(String separator, Consumer<String> separatedAnnotationsConsumer) {
    transformAndJoin(method.modifiers(), Modifier::declaration, separator, separatedAnnotationsConsumer);
  }

  private void withParameterSeparatedBy(String separator, Consumer<String> separatedAnnotationsConsumer) {
    transformTypeAndJoin(method.parameterTypes(), separator, separatedAnnotationsConsumer);
  }

  private void withTypeParametersSeparatedBy(String separator, Consumer<String> separatedArgumentsConsumer) {
    transformTypeAndJoin(method.generic().parameters(), separator, separatedArgumentsConsumer);

  }

  private void transformTypeAndJoin(Stream<TypeInstance> types, String separator, Consumer<String> joinedConsumer) {
    transformAndJoin(types, (type) -> type.declaration(), separator, joinedConsumer);
  }

  private <T> void transformAndJoin(Stream<? extends T> objects, Function<? super T, String> transformation, String separator, Consumer<String> joinedConsumer) {
    String joinedString = objects
      .map(transformation)
      .collect(Collectors.joining(separator));
    if (!joinedString.isEmpty()) {
      joinedConsumer.accept(joinedString);
    }
  }

}
