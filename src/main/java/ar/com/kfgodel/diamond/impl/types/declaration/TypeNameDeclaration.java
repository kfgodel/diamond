package ar.com.kfgodel.diamond.impl.types.declaration;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the source declaration of a type's name including all its features (as used in a variable or parameter)
 * Created by kfgodel on 04/10/14.
 */
public class TypeNameDeclaration {

  private TypeInstance type;

  public static TypeNameDeclaration create(TypeInstance type) {
    TypeNameDeclaration declaration = new TypeNameDeclaration();
    declaration.type = type;
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

    withComponentTypeDeclaration((componentTypeDeclaration) -> {
      builder.append(componentTypeDeclaration);
      // We separate the component only if it has type annotations too
      if (type.annotations().count() > 0) {
        builder.append(" ");
      }
    });
    withAnnotationsSeparatedBy(" ", (separatedAnnotations) -> {
      builder.append(separatedAnnotations);
      builder.append(" ");
    });
    builder.append(type.names().bareName());
    withTypeArgumentDeclarationsSeparatedBy(", ", (separatedArguments) -> {
      builder.append("<");
      builder.append(separatedArguments);
      builder.append(">");
    });
    withUpperBoundDeclarationsSeparatedBy(" & ", (separatedUpperBounds) -> {
      builder.append(" extends ");
      builder.append(separatedUpperBounds);
    });
    withLowerBoundDeclarationsSeparatedBy(", ", (separatedLowerBounds) -> {
      builder.append(" super ");
      builder.append(separatedLowerBounds);
    });

    return builder.toString();
  }

  private void withComponentTypeDeclaration(Consumer<String> componentTypeDeclarationConsumer) {
    Nary<String> componentDeclaration = type.componentType()
      .map(TypeInstance::declaration);
    componentDeclaration.unique().ifPresent(componentTypeDeclarationConsumer);
  }

  private void withAnnotationsSeparatedBy(String separator, Consumer<String> separatedAnnotationsConsumer) {
    transformAndJoin(
      type.annotations(),
      (annotation) -> annotation.toString(),
      separator,
      separatedAnnotationsConsumer
    );
  }

  private void withTypeArgumentDeclarationsSeparatedBy(String separator, Consumer<String> separatedArgumentsConsumer) {
    transformTypeAndJoin(type.generics().arguments(), separator, separatedArgumentsConsumer);
  }

  private void withUpperBoundDeclarationsSeparatedBy(String separator, Consumer<String> separatedArgumentsConsumer) {
    transformTypeAndJoin(type.generics().bounds().upper(), separator, separatedArgumentsConsumer);
  }

  private void withLowerBoundDeclarationsSeparatedBy(String separator, Consumer<String> separatedArgumentsConsumer) {
    transformTypeAndJoin(type.generics().bounds().lower(), separator, separatedArgumentsConsumer);
  }

  private void transformTypeAndJoin(Stream<TypeInstance> types, String separator, Consumer<String> joinedConsumer) {
    transformAndJoin(types, TypeInstance::declaration, separator, joinedConsumer);
  }

  private <T> void transformAndJoin(Stream<? extends T> objects,
                                    Function<? super T, String> transformation,
                                    String separator,
                                    Consumer<String> joinedConsumer) {
    String joinedString = objects
      .map(transformation)
      .collect(Collectors.joining(separator));
    if (!joinedString.isEmpty()) {
      joinedConsumer.accept(joinedString);
    }
  }

}
