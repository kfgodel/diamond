package ar.com.kfgodel.diamond.impl.types.lineage;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeLineage;
import ar.com.kfgodel.diamond.impl.types.iteration.AllSuperTypesSpliterator;
import ar.com.kfgodel.diamond.impl.types.iteration.RuntimeAlternativesSpliterator;
import ar.com.kfgodel.diamond.impl.types.iteration.TypeInstanceSpliterator;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.List;
import java.util.Optional;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This type represents the default implementation for a type lineage implemented
 * as an iteration strategy starting from the lowest descendant and applying a function to get higher
 *
 * Created by kfgodel on 19/09/14.
 */
public class DefaultTypeLineage implements TypeLineage {

  /**
   * List return value to indicate not found
   */
  public static final int NOT_FOUND = -1;

  private List<TypeInstance> classes;
  private Function<TypeInstance, Nary<TypeInstance>> supertypesExtractor;

  @Override
  public Nary<TypeInstance> allExtendedTypes() {
    return Nary.from(classes.stream());
  }

  @Override
  public Nary<TypeInstance> allImplementedTypes() {
    Stream<TypeInstance> memberDirectInterfaces = allExtendedTypes()
      .flatMap((member) -> member.runtime().hierarchy().interfaces());
    Stream<TypeInstance> indirectInterfaces = memberDirectInterfaces
      .flatMap((interfaz) -> Stream.concat(
        Stream.of(interfaz),
        interfaz.runtime().hierarchy().interfaces()));
    // If an indirect interface is inherited more than once, we want just one occurrence
    return Nary.from(indirectInterfaces.distinct());
  }

  @Override
  public Nary<TypeInstance> allRelatedTypes() {
    return Nary.from(
      RuntimeAlternativesSpliterator.create(
        Spliterators.iterator(AllSuperTypesSpliterator.create(lowestDescendant(), supertypesExtractor))
      )
    );
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
   * @return The index of the first member (lowest descendant)
   */
  private int firstIndex() {
    return 0;
  }

  @Override
  public Nary<TypeInstance> genericArgumentsOf(TypeInstance referenceType) {
    Optional<TypeInstance> foundType = this.allRelatedTypes()
      .filter((relatedType) ->
        referenceType.names().canonicalName().equals(relatedType.names().canonicalName())
          && relatedType.generics().arguments().count() > 0
      ).findFirst();
    return foundType
      .map((type) -> type.generics().arguments())
      .orElse(Nary.empty());
  }

  @Override
  public TypeInstance highestAncestor() {
    return classes.get(lastIndex());
  }

  /**
   * @return The index of the last member (highest ancestor)
   */
  private int lastIndex() {
    return classes.size() - 1;
  }

  @Override
  public TypeInstance lowestDescendant() {
    return classes.get(firstIndex());
  }

  /**
   * Returns a member of this lineage relative in position to given member
   *
   * @param referentClass The referent member
   * @param offset        The offset position of the asked member
   * @return The relative member or empty if there's no member on that position or
   * referent doesn't belong to this lineage
   */
  private Nary<TypeInstance> memberRelativeTo(TypeInstance referentClass, int offset) {
    int referentIndex = classes.indexOf(referentClass);
    if (referentIndex == NOT_FOUND) {
      // Class is not part of this lineage
      return Nary.empty();
    }
    int askedIndex = referentIndex + offset;
    if (askedIndex < firstIndex() || askedIndex > lastIndex()) {
      // Class is already an extreme
      return Nary.empty();
    }
    TypeInstance askedClass = classes.get(askedIndex);
    return Nary.of(askedClass);
  }

  public static DefaultTypeLineage create(TypeInstance lowest,
                                          Function<TypeInstance,
                                            Unary<? extends TypeInstance>> advanceOperation,
                                          Function<TypeInstance, Nary<TypeInstance>> supertypesExtractor) {
    DefaultTypeLineage lineage = new DefaultTypeLineage();
    lineage.classes = StreamSupport.stream(TypeInstanceSpliterator.create(lowest, advanceOperation), false)
      .collect(Collectors.toList());
    lineage.supertypesExtractor = supertypesExtractor;
    return lineage;
  }


}
