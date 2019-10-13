package ar.com.kfgodel.diamond.unit.generics;



import info.kfgodel.jspek.api.contexts.TestContext;

import java.util.List;
import java.util.function.Supplier;

/**
 * This type is the testing context for ValueSubstituter
 * Created by kfgodel on 27/09/14.
 */
public interface SubstituterTestContext extends TestContext {

    List<Object> subtypeArguments();
    void subtypeArguments(Supplier<List<Object>> definition);

    List<Object> subtypeParameters();
    void subtypeParameters(Supplier<List<Object>> definition);

    List<Object> superTypeArguments();
    void superTypeArguments(Supplier<List<Object>> definition);

    List<Object> replacedSuperTypeArguments();
    void replacedSuperTypeArguments(Supplier<List<Object>> definition);


}
