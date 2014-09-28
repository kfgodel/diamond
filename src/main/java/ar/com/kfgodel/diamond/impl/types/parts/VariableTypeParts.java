package ar.com.kfgodel.diamond.impl.types.parts;

import ar.com.kfgodel.diamond.api.types.TypeBounds;

/**
 * This type represents the parts needed to build a VariableTypeInstance
 * Created by kfgodel on 28/09/14.
 */
public interface VariableTypeParts extends CommonTypeParts {

    TypeBounds getBounds();

}
