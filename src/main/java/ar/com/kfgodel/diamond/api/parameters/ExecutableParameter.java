package ar.com.kfgodel.diamond.api.parameters;

import ar.com.kfgodel.diamond.api.DiamondReflection;
import ar.com.kfgodel.diamond.api.annotations.Annotated;
import ar.com.kfgodel.diamond.api.equals.TokenIdentifiable;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiable;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents a parameter of an executable type (a method or constructor, lambda, etc)
 *
 * Created by kfgodel on 07/11/14.
 */
public interface ExecutableParameter extends Named, Modifiable, Annotated, DiamondReflection, TokenIdentifiable {

    /**
     * The type declared for this parameter
     * @return The type representation of the expected argument for this parameter
     */
    TypeInstance declaredType();

    /**
     * Returns the name of this parameter if available on the .class file.<br>
     *     To have this information available classes must be compiled with -parameters option:
     *     http://docs.oracle.com/javase/tutorial/reflect/member/methodparameterreflection.html
     *
     *
     * @return The name of the parameter or a generic "arg0" like value
     */
    @Override
    String name();
}
