package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.cache.DiamondCache;
import ar.com.kfgodel.diamond.api.objects.MetaObject;
import ar.com.kfgodel.diamond.api.sources.MetaObjectSources;
import ar.com.kfgodel.diamond.impl.objects.MetaObjectInstance;

/**
 * This type is the implementation of the meta-object sources
 * Created by kfgodel on 17/11/14.
 */
public class MetaObjectSourcesImpl implements MetaObjectSources {

  private DiamondCache cache;

  public static MetaObjectSourcesImpl create(DiamondCache cache) {
    MetaObjectSourcesImpl sources = new MetaObjectSourcesImpl();
    sources.cache = cache;
    return sources;
  }

  @Override
  public MetaObject from(Object instance) {
    return cache.reuseOrCreateRepresentationFor(instance, () -> createRepresentationOf(instance));
  }

  private MetaObject createRepresentationOf(Object instance) {
    return MetaObjectInstance.create(instance);
  }
}
