package ar.com.kfgodel.diamond.impl.declaration;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the declaration of a type including all its features
 * Created by kfgodel on 04/10/14.
 */
public class TypeDeclaration {

    private TypeInstance type;

    public static TypeDeclaration create(TypeInstance type) {
        TypeDeclaration declaration = new TypeDeclaration();
        declaration.type = type;
        return declaration;
    }

    /**
     * Returns this declaration as a source string.<br>
     *     Depending on the type relations with other types are included (component type, type arguments, etc)
     * @return The declaration string for the involved type
     */
    public String asString() {
        StringBuilder builder = new StringBuilder();

        withComponentTypeDeclaration((componentTypeDeclaration)->{
            builder.append(componentTypeDeclaration);
            builder.append(" ");
        });
        withAnnotationsSeparatedBy(" ", (separatedAnnotations)->{
            builder.append(separatedAnnotations);
            builder.append(" ");
        });
        builder.append(type.names().bareName());
        withTypeArgumentDeclarationsSeparatedBy(", ", (separatedArguments) -> {
            builder.append("<");
            builder.append(separatedArguments);
            builder.append(">");
        });
        withUpperBoundDeclarationsSeparatedBy(" & ", (separatedUpperBounds)->{
            builder.append(" extends ");
            builder.append(separatedUpperBounds);
        });
        withLowerBoundDeclarationsSeparatedBy(", ", (separatedLowerBounds)->{
            builder.append(" super ");
            builder.append(separatedLowerBounds);
        });

        return builder.toString();
    }

    private void withComponentTypeDeclaration(Consumer<String> componentTypeDeclarationConsumer){
        Optional<String> componentDeclaration = type.componentType()
                .map((component) -> component.declaration());
        componentDeclaration.ifPresent(componentTypeDeclarationConsumer);
    }

    private void withAnnotationsSeparatedBy(String separator, Consumer<String> separatedAnnotationsConsumer){
        transformAndJoin(type.annotations(), (annotation) -> annotation.toString(), separator, separatedAnnotationsConsumer);
    }

    private void withTypeArgumentDeclarationsSeparatedBy(String separator, Consumer<String> separatedArgumentsConsumer){
        transformTypeAndJoin(type.typeArguments(), separator, separatedArgumentsConsumer);
    }

    private void withUpperBoundDeclarationsSeparatedBy(String separator, Consumer<String> separatedArgumentsConsumer){
        transformTypeAndJoin(type.bounds().upper(), separator, separatedArgumentsConsumer);
    }
    private void withLowerBoundDeclarationsSeparatedBy(String separator, Consumer<String> separatedArgumentsConsumer){
        transformTypeAndJoin(type.bounds().lower(), separator, separatedArgumentsConsumer);
    }

    private void transformTypeAndJoin(Stream<TypeInstance> types, String separator, Consumer<String> joinedConsumer){
        transformAndJoin(types, (type)-> type.declaration(), separator, joinedConsumer);
    }

    private<T> void transformAndJoin(Stream<? extends T> objects, Function<? super T, String> transformation, String separator, Consumer<String> joinedConsumer) {
        String joinedString = objects
                .map(transformation)
                .collect(Collectors.joining(separator));
        if(!joinedString.isEmpty()){
            joinedConsumer.accept(joinedString);
        }
    }

}
