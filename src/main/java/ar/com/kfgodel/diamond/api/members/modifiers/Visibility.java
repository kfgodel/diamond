package ar.com.kfgodel.diamond.api.members.modifiers;

import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.visiblity.PackageModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.visiblity.PrivateModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.visiblity.ProtectedModifier;
import ar.com.kfgodel.diamond.impl.members.modifiers.visiblity.PublicModifier;

/**
 * This type represents the visibility modifiers usable with a type member.<br>
 *     It emulates an enum like accessors
 *<br>
 * Modifier    | Class | Package | Subclass | World <br>
 * ————————————+———————+—————————+——————————+———————<br>
 * public      |  y    |    y    |    y     |   y<br>
 * ————————————+———————+—————————+——————————+———————<br>
 * protected   |  y    |    y    |    y     |   n<br>
 * ————————————+———————+—————————+——————————+———————<br>
 * no modifier |  y    |    y    |    n     |   n<br>
 * ————————————+———————+—————————+——————————+———————<br>
 * private     |  y    |    n    |    n     |   n<br>
 *
 * y: accessible
 * n: not accessible
 * Created by kfgodel on 18/10/14.
 */
public class Visibility {
    /**
     * Accessible to other types in other packages<br>
     * Inaccessible to none.
     */
    public static final PublicModifier PUBLIC = PublicModifier.create();
    /**
     * Accessible to types in same package, and child types in other packages.<br>
     * Inaccessible to non child types in other packages
     */
    public static final ProtectedModifier PROTECTED = ProtectedModifier.create();
    /**
     * Accessible to types in same package.<br>
     * Inaccessible to types in other packages (even child types)
     */
    public static final PackageModifier PACKAGE = PackageModifier.create();
    /**
     * Accessible to none.<br>
     * Inaccessible to any other type
     */
    public static final MemberModifier PRIVATE = PrivateModifier.create();

    private static final MemberModifier[] values = new MemberModifier[]{PUBLIC, PRIVATE, PROTECTED, PACKAGE};

    public static MemberModifier[] values(){
        return values;
    }
}
