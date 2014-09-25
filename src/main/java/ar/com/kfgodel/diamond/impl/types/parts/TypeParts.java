package ar.com.kfgodel.diamond.impl.types.parts;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.TypeDefinedTypeNamesSource;
import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents the part of a type (any) that can hold the state needed to create a specific type.<br>
 * By using an instance of this class the state can be defined in delayed steps while checking that everything needed is
 * present on construction time
 * Created by kfgodel on 21/09/14.
 */
public class TypeParts {

    private TypeDefinedTypeNamesSource names;
    private Supplier<Optional<ClassInstance>> superclassSupplier;
    private Annotation[] annotations;
    private Optional<TypeInstance> componentType;
    private List<TypeInstance> typeArguments;
    private TypeBounds bounds;

    public TypeDefinedTypeNamesSource getNames() {
        if(names == null){
            throw new DiamondException("Names is needed to create a type instance");
        }
        return names;
    }

    public void setNames(TypeDefinedTypeNamesSource names) {
        this.names = names;
    }

    public Supplier<Optional<ClassInstance>> getSuperclassSupplier() {
        if (superclassSupplier == null){
            throw new DiamondException("superclass supplier is needed to create a type instance");
        }
        return superclassSupplier;
    }

    public void setSuperclassSupplier(Supplier<Optional<ClassInstance>> superclassSupplier) {
        this.superclassSupplier = superclassSupplier;
    }

    public Annotation[] getAnnotations() {
        if(annotations == null){
            throw new DiamondException("annotations are needed to create a type instance");
        }
        return annotations;
    }

    public void setAnnotations(Annotation[] annotations) {
        this.annotations = annotations;
    }


    public Optional<TypeInstance> getComponentType() {
        if(componentType == null){
            throw new DiamondException("componentType is needed to create a type instance");
        }
        return componentType;
    }

    public void setComponentType(Optional<TypeInstance> componentType) {
        this.componentType = componentType;
    }

    public List<TypeInstance> getTypeArguments() {
        if(typeArguments == null){
            throw new DiamondException("typeArguments is needed to create a type instance");
        }
        return typeArguments;
    }

    public void setTypeArguments(List<TypeInstance> typeArguments) {
        this.typeArguments = typeArguments;
    }

    public TypeBounds getBounds() {
        if(bounds == null){
            throw new DiamondException("bounds are needed to create a type instance");
        }
        return bounds;
    }

    public void setBounds(TypeBounds bounds) {
        this.bounds = bounds;
    }

    public static TypeParts create() {
        TypeParts typeParts = new TypeParts();
        return typeParts;
    }

}
