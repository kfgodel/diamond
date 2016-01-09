package ar.com.kfgodel.diamond.impl.methods.bound;

import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.methods.BoundMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.impl.members.bound.BoundMemberSupport;
import ar.com.kfgodel.diamond.impl.strings.DebugPrinter;

/**
 * This type represents a method bound to an instance
 * Created by kfgodel on 16/11/14.
 */
public class BoundMethodInstance extends BoundMemberSupport implements BoundMethod {

    private TypeMethod method;

    @Override
    public TypeMethod typeMethod() {
        return method;
    }

    @Override
    public TypeMember typeMember() {
        return typeMethod();
    }

    @Override
    public Object invoke(Object... arguments) {
        return method.invokeOn(instance(), arguments);
    }

    public static BoundMethodInstance create(TypeMethod method, Object instance) {
        BoundMethodInstance boundMethod = new BoundMethodInstance();
        boundMethod.setInstance(instance);
        boundMethod.method = method;
        return boundMethod;
    }

    @Override
    public String toString() {
        return DebugPrinter.print(this);
    }

}
