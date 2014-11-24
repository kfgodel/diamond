package ar.com.kfgodel.diamond.impl.types.equality;

import ar.com.kfgodel.diamond.api.equals.EqualsStructure;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.equals.CompositeEqualityToken;
import ar.com.kfgodel.hashcode.Hashcodes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This type represents the reification of the equality concept for types
 * Created by kfgodel on 04/10/14.
 */
public class TypeEquality {

    public static final TypeEquality INSTANCE = new TypeEquality();

    public boolean areEquals(TypeInstance one, Object obj){
        if(one == obj){
            return true;
        }
        if(one == null || obj == null || !(obj instanceof TypeInstance) || one.hashCode() != obj.hashCode()){
            return false;
        }
        TypeInstance other = (TypeInstance) obj;
        return one.getIdentityToken().equals(other.getIdentityToken());
    }

    /**
     * Calculates the hashcode of a type to be consistent with equals definition
     * @param type The type to calculate its hashcode
     * @return The hash of its name, component type, type arguments, and bounds
     */
    public int hashcodeFor(TypeInstance type){
        return Hashcodes.joining(
                type.names().bareName(),
                Hashcodes.forElementsIn(type.componentType()),
                Hashcodes.forElementsIn(type.generics().arguments()),
                Hashcodes.forElementsIn(type.generics().bounds().lower()),
                Hashcodes.forElementsIn(type.generics().bounds().upper())
                );
    }

    public EqualsStructure calculateTokenFor(TypeInstance typeInstance) {
        List<Object> typeParts = new ArrayList<>();
        typeParts.add(typeInstance.names().bareName());
        typeInstance.componentType().ifPresent(typeParts::add);
        typeParts.add(CompositeEqualityToken.create(typeInstance.generics().arguments().collect(Collectors.toList())));
        typeParts.add(CompositeEqualityToken.create(typeInstance.generics().bounds().upper().collect(Collectors.toList())));
        typeParts.add(CompositeEqualityToken.create(typeInstance.generics().bounds().lower().collect(Collectors.toList())));
        return CompositeEqualityToken.create(typeParts);
    }
}
