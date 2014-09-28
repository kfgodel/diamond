package ar.com.kfgodel.diamond.impl.types.parts;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.TypeNames;
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
public class TypeParts implements FixedTypeParts, VariableTypeParts {

    private TypeNames names;
    private Supplier<Optional<ClassInstance>> superclassSupplier;
    private Supplier<Optional<ClassInstance>> extendedTypeSupplier;
    private Annotation[] annotations;
    private Optional<TypeInstance> componentType;
    private TypeBounds bounds;
    private Supplier<List<TypeInstance>> typeParametersSupplier;
    private List<TypeInstance> typeArguments;
    private List<TypeInstance> subtypeArguments;

    public TypeNames getNames() {
        if(names == null){
            throw new DiamondException("Names is needed to create a type instance");
        }
        return names;
    }

    public void setNames(TypeNames names) {
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


    public Supplier<List<TypeInstance>> getTypeParametersSupplier() {
        if(typeParametersSupplier == null){
            throw new DiamondException("type parameters are needed to create a type instance");
        }
        return typeParametersSupplier;
    }

    public void setTypeParametersSupplier(Supplier<List<TypeInstance>> typeParametersSupplier) {
        this.typeParametersSupplier = typeParametersSupplier;
    }


    public Supplier<Optional<ClassInstance>> getExtendedTypeSupplier() {
        if(extendedTypeSupplier == null){
            throw new DiamondException("extended type is needed to create a type instance");
        }
        return extendedTypeSupplier;
    }

    public void setExtendedTypeSupplier(Supplier<Optional<ClassInstance>> extendedTypeSupplier) {
        this.extendedTypeSupplier = extendedTypeSupplier;
    }

    public static TypeParts create() {
        TypeParts typeParts = new TypeParts();
        return typeParts;
    }

}
