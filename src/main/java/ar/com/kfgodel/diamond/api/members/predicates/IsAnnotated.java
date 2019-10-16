package ar.com.kfgodel.diamond.api.members.predicates;

import ar.com.kfgodel.diamond.api.members.TypeMember;

import java.lang.annotation.Annotation;
import java.util.function.Predicate;

/**
 * This type represents the filter that allows selecting members that have an annotation type
 * Created by kfgodel on 15/11/14.
 */
public class IsAnnotated implements Predicate<TypeMember> {

  private Class<? extends Annotation>[] annotationTypes;

  @Override
  public boolean test(TypeMember member) {
    return member.annotations().anyMatch(this::isExpectedAnnotation);
  }

  /**
   * indicates if the given annotation is from one of the expected types
   *
   * @param annotation The annotation to test
   * @return true if an expected annotation
   */
  private boolean isExpectedAnnotation(Annotation annotation) {
    for (Class<? extends Annotation> annotationType : annotationTypes) {
      if (annotationType.isInstance(annotation)) {
        return true;
      }
    }
    return false;
  }

  public static IsAnnotated with(Class<? extends Annotation>... annotationTypes) {
    IsAnnotated annotated = new IsAnnotated();
    annotated.annotationTypes = annotationTypes;
    return annotated;
  }

}
