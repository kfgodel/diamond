package ar.com.kfgodel.diamond.showcase.objects;

import java.util.NavigableMap;

/**
 * This class serves to demonstrate type parametrization in a hierarchy
 * Date: 25/4/20 - 20:00
 */
public abstract class ChildClass extends ParentClass<Integer> implements NavigableMap<Integer, String> {
}
