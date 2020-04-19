package ar.com.kfgodel.diamond.api;

import ar.com.kfgodel.diamond.api.cache.DiamondCache;
import ar.com.kfgodel.diamond.api.sources.ConstructorSources;
import ar.com.kfgodel.diamond.api.sources.FieldSources;
import ar.com.kfgodel.diamond.api.sources.LambdaSources;
import ar.com.kfgodel.diamond.api.sources.MetaObjectSources;
import ar.com.kfgodel.diamond.api.sources.MethodSources;
import ar.com.kfgodel.diamond.api.sources.ModifierSources;
import ar.com.kfgodel.diamond.api.sources.PackageSources;
import ar.com.kfgodel.diamond.api.sources.ParameterSources;
import ar.com.kfgodel.diamond.api.sources.TypeSources;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.DiamondApi;

import java.lang.reflect.Type;

/**
 * This type represents the access point to Diamond API
 * Created by kfgodel on 17/09/14.
 */
public interface Diamond {

  /**
   * Singleton instance to avoid creation
   */
  DiamondApi API = DiamondApi.create();


  /**
   * @param nativeClass The native class instance to base on
   * @return A diamond class instance representation
   */
  static TypeInstance of(Type nativeClass) {
    return types().from(nativeClass);
  }

  /**
   * Utility method to quickly covert a set of native classes into their diamond representation
   *
   * @param nativeClasses Native types
   * @return The array of diamond representation
   */
  static TypeInstance[] ofNative(Type... nativeClasses) {
    TypeInstance[] types = new TypeInstance[nativeClasses.length];
    for (int i = 0; i < nativeClasses.length; i++) {
      Type nativeClass = nativeClasses[i];
      types[i] = Diamond.of(nativeClass);
    }
    return types;
  }

  /**
   * Converts the given native reflection API object into a Diamond representation.<br>
   * - Classes and types are converted into TypeInstance.<br>
   * - Methods are converted into TypeMethods.<br>
   * - Fields into TypeFields.<br>
   * - Constructors into TypeConstructor.<br>
   * - int modifiers into Modifiers.<br>
   * - Parameters into ExecutableParameters<br>
   * <br>
   * This a generic entry point that delegates the conversion to more specific versions, like methods().from(Method).,
   * Use this entry point as a all-purpose converter, or to reduce verbosity
   *
   * @param nativeReflection The native reflection object that represent a code element
   * @param <T>              The type of expected diamond instance
   * @return The diamond representation for the given element
   */
  static <T extends DiamondReflection> T from(Object nativeReflection) {
    return API.from(nativeReflection);
  }

  /**
   * @return An accessor to obtain instances that represent types
   */
  static TypeSources types() {
    return API.types();
  }

  /**
   * @return An accessor to obtain instances of methods that belong to a class
   */
  static MethodSources methods() {
    return API.methods();
  }

  /**
   * @return An accessor to obtain instances of fields that belong to a class
   */
  static FieldSources fields() {
    return API.fields();
  }

  /**
   * @return An accessor to obtain instances that represent type constructors
   */
  static ConstructorSources constructors() {
    return API.constructors();
  }

  /**
   * @return An accessor to obtain instances that represent type member modifiers
   */
  static ModifierSources modifiers() {
    return API.modifiers();
  }

  /**
   * @return An accessor to obtain instances that represent parameters
   */
  static ParameterSources parameters() {
    return API.parameters();
  }

  /**
   * @return An accessor to obtain instances that represent packages
   */
  static PackageSources packages() {
    return API.packages();
  }

  /**
   * @return The cache instance used to reference reused instances
   */
  static DiamondCache cache() {
    return API.getCache();
  }

  /**
   * @return An accessor to obtain meta-object instances that represent a meta-level view of an object
   */
  static MetaObjectSources metaObjects() {
    return API.metaObjects();
  }

  /**
   * @return An accessor to obtain lambda reification that represent a meta-level view of the lambdas
   */
  static LambdaSources lambdas() {
    return API.lambdas();
  }
}
