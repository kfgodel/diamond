package ar.com.kfgodel.diamond.api.equals;

/**
 * This object represents the equals structure of another object that describes the parts to be compared with other object
 * Created by kfgodel on 23/11/14.
 */
public interface EqualsStructure {
    /**
     * Returns the array of parts that compose this structure
     * @return The parts to compare
     */
    Object[] getParts();
}
