package ar.com.kfgodel.diamond.impl.members.modifiers.visiblity;

import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiers;

import java.util.stream.Stream;

/**
 * This type represents the private visibility member modifier
 * Created by kfgodel on 18/10/14.
 */
public class PackageModifier implements Modifier {

    public static PackageModifier create() {
        PackageModifier modifier = new PackageModifier();
        return modifier;
    }

    @Override
    public boolean isPresentIn(int modifierBitmap) {
        boolean otherVisibilityModifierIsPresent =
                Modifiers.PUBLIC.isPresentIn(modifierBitmap) ||
                Modifiers.PRIVATE.isPresentIn(modifierBitmap) ||
                Modifiers.PROTECTED.isPresentIn(modifierBitmap);
        return !otherVisibilityModifierIsPresent;
    }

    @Override
    public String declaration() {
        return "";
    }

    @Override
    public boolean test(Modifier modifier) {
        return this.equals(modifier);
    }

    @Override
    public boolean equals(Object obj) {
        return Stream.of(obj)
                .filter((object) -> object instanceof Modifier)
                .map(Modifier.class::cast)
                .filter((other) -> this.declaration().equals(other.declaration()) )
                .findAny().isPresent();
    }

    @Override
    public int hashCode() {
        return declaration().hashCode();
    }
}
