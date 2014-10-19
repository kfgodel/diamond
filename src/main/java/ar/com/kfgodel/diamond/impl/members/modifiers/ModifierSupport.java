package ar.com.kfgodel.diamond.impl.members.modifiers;

import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;

/**
 * This type serves as a base class for modifiers
 * Created by kfgodel on 18/10/14.
 */
public class ModifierSupport implements MemberModifier {

    private int nativeCode;

    protected ModifierSupport(int nativeCode){
        this.nativeCode = nativeCode;
    }

    @Override
    public boolean isPresentIn(int modifierBitmap) {
        return (nativeCode & modifierBitmap) != 0;
    }
}
