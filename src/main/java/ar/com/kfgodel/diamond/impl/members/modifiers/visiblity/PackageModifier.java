package ar.com.kfgodel.diamond.impl.members.modifiers.visiblity;

import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.sources.modifiers.Visibility;

/**
 * This type represents the private visibility member modifier
 * Created by kfgodel on 18/10/14.
 */
public class PackageModifier implements MemberModifier{

    public static PackageModifier create() {
        PackageModifier modifier = new PackageModifier();
        return modifier;
    }

    @Override
    public boolean isPresentIn(int modifierBitmap) {
        boolean otherVisibilityModifierIsPresent =
                Visibility.PUBLIC.isPresentIn(modifierBitmap) ||
                Visibility.PRIVATE.isPresentIn(modifierBitmap) ||
                Visibility.PROTECTED.isPresentIn(modifierBitmap);
        return !otherVisibilityModifierIsPresent;
    }
}
