package ar.com.kfgodel.diamond.api.lambdas;

import ar.com.kfgodel.diamond.api.DiamondReflection;
import ar.com.kfgodel.diamond.api.executables.Executable;

/**
 * This type represents a lambda reification that can answer questions about the lambda structure.<br>
 *     Also the possibility to execute the underlying lambda with a single method signature for all the possible variations
 * Created by kfgodel on 01/02/15.
 */
public interface Lambda extends Executable, DiamondReflection {
    
}
