package ar.com.kfgodel.diamond.impl.fields.declaration;

import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the source declaration for a field
 * Created by kfgodel on 01/11/14.
 */
public class FieldDeclaration {

    private TypeField field;

    public static FieldDeclaration create(TypeField field) {
        FieldDeclaration declaration = new FieldDeclaration();
        declaration.field = field;
        return declaration;
    }

    /**
     * Returns this declaration as a source string.<br>
     *     Depending on the type relations with other types are included (component type, type arguments, etc)
     * @return The declaration string for the involved type
     */
    public String asString() {
        StringBuilder builder = new StringBuilder();

        withAnnotationsSeparatedBy(" ", (separatedAnnotations)->{
            builder.append(separatedAnnotations);
            builder.append(" ");
        });
        withModifiersSeparatedBy(" ", (separatedModifiers)->{
            builder.append(separatedModifiers);
            builder.append(" ");
        });
//        withTypeParametersSeparatedBy(", ", (separatedTypeParameters) -> {
//            builder.append("<");
//            builder.append(separatedTypeParameters);
//            builder.append("> ");
//        });
        builder.append(field.type().name());
        builder.append(" ");
        builder.append(field.name());
        builder.append(" /* ");
        builder.append(field.declaringType().name());
        builder.append(" */");

        return builder.toString();
    }

    private void withAnnotationsSeparatedBy(String separator, Consumer<String> separatedAnnotationsConsumer){
        transformAndJoin(field.annotations(), Object::toString, separator, separatedAnnotationsConsumer);
    }

    private void withModifiersSeparatedBy(String separator, Consumer<String> separatedAnnotationsConsumer){
        transformAndJoin(field.modifiers(), MemberModifier::declaration, separator, separatedAnnotationsConsumer);
    }


//    private void withTypeParametersSeparatedBy(String separator, Consumer<String> separatedArgumentsConsumer){
//        transformTypeAndJoin(field.generics().typeArguments(), separator, separatedArgumentsConsumer);
//    }

    private<T> void transformAndJoin(Stream<? extends T> objects, Function<? super T, String> transformation, String separator, Consumer<String> joinedConsumer) {
        String joinedString = objects
                .map(transformation)
                .collect(Collectors.joining(separator));
        if(!joinedString.isEmpty()){
            joinedConsumer.accept(joinedString);
        }
    }

}
