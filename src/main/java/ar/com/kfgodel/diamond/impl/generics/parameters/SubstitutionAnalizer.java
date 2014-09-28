package ar.com.kfgodel.diamond.impl.generics.parameters;

import java.util.ArrayList;
import java.util.List;

/**
 * This type represents a substitution analizer that search in one list values substituted from the other and reports
 * those substitutions. <br> The substitutions can then be used to repeat them in other context
 * Created by kfgodel on 27/09/14.
 */
public class SubstitutionAnalizer {

    /**
     * Search for occurrences of replacementValues in the replacedValues list and collects all the
     * replacements made.
     * @param replacementValues The values that can be found on the replacedValues
     * @param replacedValues The values that could be replaced by the replacementValues
     * @return The list of substitutions found on the replacedValues
     */
    public static List<ValueSubstitution> analyze(List<?> replacementValues, List<?> replacedValues){
        List<ValueSubstitution> substitutions = new ArrayList<>(replacedValues.size());
        for (int replacedIndex = 0; replacedIndex < replacedValues.size(); replacedIndex++) {
            Object replacedValue = replacedValues.get(replacedIndex);
            int replacementIndex = replacementValues.indexOf(replacedValue);
            if(replacementIndex >= 0){
                substitutions.add(ValueSubstitution.create(replacedIndex, replacementIndex));
            }
        }
        return substitutions;
    }
}
