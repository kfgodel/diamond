package ar.com.kfgodel.diamond.showcase.objects;

import java.util.SortedMap;

/**
 * This class serves to demonstrate type argument calculation
 * Date: 25/4/20 - 19:58
 */
public abstract class ParentClass<K extends Number> extends GrandpaClass<K, String> implements SortedMap<K, String> {
}
