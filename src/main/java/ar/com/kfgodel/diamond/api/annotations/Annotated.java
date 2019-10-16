package ar.com.kfgodel.diamond.api.annotations;

import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;

/**
 * This type represents an object that carries annotations with it.<br>
 * Types, fields, methods, etc
 * Created by kfgodel on 20/09/14.
 */
public interface Annotated {

  /**
   * @return The set of annotations represents in this object
   */
  Nary<Annotation> annotations();
}
