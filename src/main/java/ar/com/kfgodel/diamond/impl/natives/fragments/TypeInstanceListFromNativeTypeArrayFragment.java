package ar.com.kfgodel.diamond.impl.natives.fragments;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This type represents the converter from native type arrays to diamond type lists
 * Created by kfgodel on 01/11/14.
 */
public class TypeInstanceListFromNativeTypeArrayFragment {

  /**
   * Takes an array of native types and converts each object to its diamond representation
   *
   * @param nativeTypes The array of native types
   * @return The Diamond type list
   */
  public static List<TypeInstance> apply(Object[] nativeTypes) {
    return Arrays.stream(nativeTypes)
      .map((nativeType) -> Diamond.types().from(nativeType))
      .collect(Collectors.toList());
  }
}
