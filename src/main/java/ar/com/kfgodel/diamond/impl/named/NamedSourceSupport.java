package ar.com.kfgodel.diamond.impl.named;

import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.naming.NamedSource;
import ar.com.kfgodel.nary.api.Nary;

import java.util.stream.Stream;

/**
 * This type is a base class for sources of named elements, that adds some common source retriever methods
 * Created by kfgodel on 25/10/14.
 */
public abstract class NamedSourceSupport<N extends Named> implements NamedSource<N> {

  protected abstract Stream<N> getAll();

  @Override
  public Nary<N> named(String elementName) {
    return Nary.create(getAll().filter((element) -> element.name().equals(elementName)));
  }


}
