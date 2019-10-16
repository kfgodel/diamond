package ar.com.kfgodel.diamond.impl.members.bound;

import ar.com.kfgodel.diamond.api.members.BoundMember;

/**
 * This type serves as base class for bound members
 * Created by kfgodel on 09/01/16.
 */
public abstract class BoundMemberSupport implements BoundMember {

  private Object instance;

  @Override
  public Object instance() {
    return instance;
  }

  protected void setInstance(Object instance) {
    this.instance = instance;
  }

  @Override
  public String name() {
    return typeMember().name();
  }

  @Override
  public boolean equals(Object obj) {
    return BoundMemberEquality.INSTANCE.areEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return BoundMemberEquality.INSTANCE.hash(this);
  }
}
