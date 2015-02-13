package ar.com.kfgodel.diamond.impl.types.lineage;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.impl.types.iteration.AllSuperTypesSpliterator;
import ar.com.kfgodel.diamond.impl.types.iteration.RuntimeAlternativesSpliterator;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.Spliterators;

/**
 * This type serves as base class for type lineage implementations
 * Created by kfgodel on 13/02/15.
 */
public abstract class TypeLineageSupport implements TypeLineage {

    @Override
    public Nary<TypeInstance> allRelatedTypes() {
        return NaryFromNative.create(
                RuntimeAlternativesSpliterator.create(
                        Spliterators.iterator(AllSuperTypesSpliterator.create(lowestDescendant()))));
    }

}
