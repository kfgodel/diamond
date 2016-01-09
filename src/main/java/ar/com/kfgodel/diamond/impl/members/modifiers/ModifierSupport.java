package ar.com.kfgodel.diamond.impl.members.modifiers;

import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;

/**
 * This type serves as a base class for modifiers
 * Created by kfgodel on 18/10/14.
 */
public class ModifierSupport implements Modifier {

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
    public boolean test(Modifier modifier) {
        return this.equals(modifier);
    }


    @Override
    public int hashCode() {
        return ModifierEquality.INSTANCE.hashcodeFor(this);
    }

    @Override
    public boolean equals(Object obj) {
        return ModifierEquality.INSTANCE.areEquals(this, obj);
    }

    @Override
    public String toString() {
        return declaration();
    }
}
