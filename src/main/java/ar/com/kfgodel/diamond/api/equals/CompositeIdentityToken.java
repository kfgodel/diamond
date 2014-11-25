package ar.com.kfgodel.diamond.api.equals;

/**
 * This object represents an identity token that is composed of other parts
 * Created by kfgodel on 23/11/14.
 */
public interface CompositeIdentityToken {
    /**
     * Returns the array of parts that compose this structure
     * @return The parts to compare
     */
    Object[] getParts();
}
