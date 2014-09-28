package ar.com.kfgodel.diamond.impl.classes;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.classes.ClassLineage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This type represents a class lineage calculated from native class instance
 * Created by kfgodel on 19/09/14.
 */
public class NativeClassLineage implements ClassLineage {

    /**
     * List return value to indicate not found
     */
    public static final int NOT_FOUND = -1;

    private List<ClassInstance> classes;

    @Override
    public ClassInstance lowestDescendant() {
        return classes.get(firstIndex());
    }

    @Override
    public ClassInstance highestAncestor() {
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
    public Stream<ClassInstance> allMembers() {
        return classes.stream();
    }

    @Override
    public Optional<ClassInstance> ancestorOf(ClassInstance descendant) {
        return memberRelativeTo(descendant, +1);
    }

    @Override
    public Optional<ClassInstance> descendantOf(ClassInstance ancestor) {
        return memberRelativeTo(ancestor, -1);
    }

    /**
     * Returns a member of this lineage relative in position to given member
     * @param referentClass The referent member
     * @param offset The offset position of the asked member
     * @return The relative member or empty if there's no member on that position or referent doesn't belong to this lineage
     */
    private Optional<ClassInstance> memberRelativeTo(ClassInstance referentClass, int offset) {
        int referentIndex = classes.indexOf(referentClass);
        if(referentIndex == NOT_FOUND){
            // Class is not part of this lineage
            return Optional.empty();
        }
        int askedIndex = referentIndex + offset;
        if(askedIndex < firstIndex() || askedIndex > lastIndex()){
            // Class is already an extreme
            return Optional.empty();
        }
        ClassInstance askedClass = classes.get(askedIndex);
        return Optional.of(askedClass);
    }


    public static NativeClassLineage create(ClassInstance lowestDescendant) {
        NativeClassLineage lineage = new NativeClassLineage();
        lineage.classes = StreamSupport.stream(ExtendedTypeSpliterator.create(lowestDescendant), false).collect(Collectors.toList());
        return lineage;
    }

}
