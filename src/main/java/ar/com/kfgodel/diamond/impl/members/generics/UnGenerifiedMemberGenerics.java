package ar.com.kfgodel.diamond.impl.members.generics;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents generics information for a non generified member
 * Created by kfgodel on 01/11/14.
 */
public class UnGenerifiedMemberGenerics implements Generics {


  private static final UnGenerifiedMemberGenerics INSTANCE = new UnGenerifiedMemberGenerics();

  public static UnGenerifiedMemberGenerics instance() {
    return INSTANCE;
  }

  @Override
  public Nary<TypeInstance> parameters() {
    return Nary.empty();
  }
}
