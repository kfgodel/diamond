package ar.com.kfgodel.diamond.impl.types.lineage;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.impl.types.iteration.AllSuperTypesSpliterator;
import ar.com.kfgodel.diamond.impl.types.iteration.TypeInstanceSpliterator;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This type represents a class lineage calculated from native class instance
 * Created by kfgodel on 19/09/14.
 */
public class FunctionBasedTypeLineage implements TypeLineage {

    /**
     * List return value to indicate not found
     */
    public static final int NOT_FOUND = -1;

    private List<TypeInstance> classes;

    @Override
    public TypeInstance lowestDescendant() {
        return classes.get(firstIndex());
    }

    @Override
    public TypeInstance highestAncestor() {
        return classes.get(lastIndex());
    }

    /**
     * @return The index of the first member (lowest descendant)
     */
    private int firstIndex() {
        return 0;
    }

    /**
     * @return The index of the last member (highest ancestor)
     */
    private int lastIndex() {
        return classes.size() -1;
    }

    @Override
    public Nary<TypeInstance> allMembers() {
        return NaryFromNative.create(classes.stream());
    }

    @Override
    public Nary<TypeInstance> ancestorOf(TypeInstance descendant) {
        return memberRelativeTo(descendant, +1);
    }

    @Override
    public Nary<TypeInstance> descendantOf(TypeInstance ancestor) {
        return memberRelativeTo(ancestor, -1);
    }

    /**
     * Returns a member of this lineage relative in position to given member
     * @param referentClass The referent member
     * @param offset The offset position of the asked member
     * @return The relative member or empty if there's no member on that position or referent doesn't belong to this lineage
     */
    private Nary<TypeInstance> memberRelativeTo(TypeInstance referentClass, int offset) {
        int referentIndex = classes.indexOf(referentClass);
        if(referentIndex == NOT_FOUND){
            // Class is not part of this lineage
            return NaryFromNative.empty();
        }
        int askedIndex = referentIndex + offset;
        if(askedIndex < firstIndex() || askedIndex > lastIndex()){
            // Class is already an extreme
            return NaryFromNative.empty();
        }
        TypeInstance askedClass = classes.get(askedIndex);
        return NaryFromNative.of(askedClass);
    }

    @Override
    public Nary<TypeInstance> inheritedInterfaces() {
        Stream<TypeInstance> memberDirectInterfaces = allMembers()
                .flatMap((member) -> member.inheritance().interfaces());
        Stream<TypeInstance> indirectInterfaces = memberDirectInterfaces
                .flatMap((interfaz) -> Stream.concat(
                        Stream.of(interfaz),
                        interfaz.inheritance().interfaces()));
        // If an indirect interface is inherited more than once, we want just one occurrence
        return NaryFromNative.create(indirectInterfaces.distinct());
    }

    @Override
    public Nary<TypeInstance> allRelatedTypes() {
        return NaryFromNative.create(AllSuperTypesSpliterator.create(lowestDescendant()));
    }

    public static FunctionBasedTypeLineage create(TypeInstance lowest, Function<TypeInstance, Nary<? extends TypeInstance>> advanceOperation) {
        FunctionBasedTypeLineage lineage = new FunctionBasedTypeLineage();
        lineage.classes = StreamSupport.stream(TypeInstanceSpliterator.create(lowest, advanceOperation), false).collect(Collectors.toList());
        return lineage;
    }


}
