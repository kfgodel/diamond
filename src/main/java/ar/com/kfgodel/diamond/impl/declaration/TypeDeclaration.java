package ar.com.kfgodel.diamond.impl.declaration;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Optional;
import java.util.stream.Collectors;

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
     * Returns this declaration as a source string
     * @return The declaration string for the involved type
     */
    public String asString() {
        StringBuilder builder = new StringBuilder();
        Optional<String> componentDeclaration = type.componentType().map((component) -> component.declaration());
        componentDeclaration.ifPresent((aDeclaration)->{
            builder.append(aDeclaration);
            builder.append(" ");
        });
        String typeAnnotations = type.annotations()
                .map((annotation) -> annotation.toString())
                .collect(Collectors.joining(" "));
        if(!typeAnnotations.isEmpty()){
            builder.append(typeAnnotations);
            builder.append(" ");
        }
        builder.append(type.names().bareName());
        String typeArguments = type.typeArguments().map((argument) -> argument.declaration())
                .collect(Collectors.joining(", "));
        if(!typeArguments.isEmpty()){
            builder.append("<");
            builder.append(typeArguments);
            builder.append(">");
        }
        TypeBounds typeBounds = type.bounds();
        String upperBounds = typeBounds.upper().map((upper) -> upper.declaration())
                .collect(Collectors.joining(" & "));
        if(!upperBounds.isEmpty()){
            builder.append(" extends ");
            builder.append(upperBounds);
        }

        String lowerBounds = typeBounds.lower().map((lower) -> lower.declaration())
                .collect(Collectors.joining(", "));
        if(!lowerBounds.isEmpty()){
            builder.append(" super ");
            builder.append(lowerBounds);
        }

        return builder.toString();
    }
}
