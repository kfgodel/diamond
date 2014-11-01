package ar.com.kfgodel.diamond.impl.members.modifiers;

import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;

/**
 * This type serves as a base class for modifiers
 * Created by kfgodel on 18/10/14.
 */
public class ModifierSupport implements MemberModifier {

    private int nativeCode;
    private String declaration;

    protected ModifierSupport(int nativeCode, String declaration){
        this.nativeCode = nativeCode;
        this.declaration = declaration;
    }

    @Override
    public boolean isPresentIn(int modifierBitmap) {
        return (nativeCode & modifierBitmap) != 0;
    }

    @Override
    public String declaration() {
        return declaration;
    }


    @Override
    public boolean test(MemberModifier memberModifier) {
        return this.equals(memberModifier);
    }
}
