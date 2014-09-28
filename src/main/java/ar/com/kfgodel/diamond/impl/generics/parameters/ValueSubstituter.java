package ar.com.kfgodel.diamond.impl.generics.parameters;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * This type represents a value substituter that replaces list values based on a substitute list and a set of
 * pre-defined substitutions
 * Created by kfgodel on 27/09/14.
 */
public class ValueSubstituter<T> implements BiConsumer<List<T>, List<T>> {

    private List<ValueSubstitution> substitutions;

    public static<T> ValueSubstituter<T> create(List<ValueSubstitution> substitutions) {
        ValueSubstituter<T> substituter = new ValueSubstituter<>();
        substituter.substitutions = substitutions;
        return substituter;
    }

    @Override
    public void accept(List<T> replacementValues, List<T> replacedValues) {
        for (ValueSubstitution substitution : substitutions) {
            substitution.executeOn(replacementValues, replacedValues);
        }
    }
}
