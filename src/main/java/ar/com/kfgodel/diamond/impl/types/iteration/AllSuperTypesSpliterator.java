package ar.com.kfgodel.diamond.impl.types.iteration;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This type represents the spliterator that traverses all the supertypes of a given type, avoiding duplicates
 * Created by kfgodel on 16/11/14.
 */
public class AllSuperTypesSpliterator implements Spliterator<TypeInstance> {

  private Set<TypeInstance> visitedTypes;
  private Queue<TypeInstance> pendingTypes;
  private TypeInstance lastVisitedType;
  private Function<TypeInstance, Nary<TypeInstance>> supertypesExtractor;

  @Override
  public boolean tryAdvance(Consumer<? super TypeInstance> action) {
    //Not executed the first nor the last time
    if (lastVisitedType != null) {
      // We explore the supertypes of the last visited type for the next ones
      addNonVisitedSuperTypesAsPending(lastVisitedType);
      lastVisitedType = null;
    }
    if (pendingTypes.isEmpty()) {
      // No more elements
      return false;
    }
    TypeInstance currentType = pendingTypes.poll();
    visitedTypes.add(currentType);
    action.accept(currentType);
    lastVisitedType = currentType;
    return true;
  }

  private void addNonVisitedSuperTypesAsPending(TypeInstance currentType) {
    final Nary<TypeInstance> supertypes = supertypesExtractor.apply(currentType);
    supertypes
      .filter((superType) -> !visitedTypes.contains(superType))
      .filter((nonVisited) -> !pendingTypes.contains(nonVisited))
      .forEach((notAlreadyPending) -> pendingTypes.add(notAlreadyPending));
  }

  @Override
  public Spliterator<TypeInstance> trySplit() {
    return null;
  }

  @Override
  public long estimateSize() {
    return Long.MAX_VALUE;
  }

  @Override
  public int characteristics() {
    return Spliterator.DISTINCT & Spliterator.IMMUTABLE & Spliterator.NONNULL & Spliterator.ORDERED;
  }


  public static AllSuperTypesSpliterator create(TypeInstance startingType,
                                                Function<TypeInstance, Nary<TypeInstance>> supertypesExtractor) {
    AllSuperTypesSpliterator spliterator = new AllSuperTypesSpliterator();
    spliterator.pendingTypes = new LinkedList<>();
    spliterator.visitedTypes = new HashSet<>();
    spliterator.pendingTypes.add(startingType);
    spliterator.supertypesExtractor = supertypesExtractor;
    return spliterator;
  }

}
