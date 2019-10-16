package ar.com.kfgodel.diamond.api.declaration;

/**
 * This type represents an object than can be declared in a source file (has a declaration)
 * Created by kfgodel on 01/11/14.
 */
public interface Declarable {

  /**
   * @return The source code declaration equivalent of this instance (or approximate)
   */
  String declaration();
}
