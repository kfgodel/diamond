package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.cache.DiamondCache;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.TypeSources;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.impl.natives.raws.RawClassesCalculator;
import ar.com.kfgodel.diamond.impl.types.FixedTypeInstance;
import ar.com.kfgodel.diamond.impl.types.VariableTypeInstance;
import ar.com.kfgodel.diamond.impl.types.description.TypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.description.extended.ExtendedTypeDescription;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.ActualArgumentReplacer;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.SupertypeParametrizationAnalyzer;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.parametrization.SupertypeParametrization;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This type implements the sources for type instances
 * Created by kfgodel on 20/09/14.
 */
public class TypeSourceImpl implements TypeSources {

  private DiamondCache cache;
  private TypeDescriptor descriptor;

  @Override
  public TypeInstance fromDescription(TypeDescription description) {
    if (description.isForVariableType()) {
      return VariableTypeInstance.create(description);
    }
    return FixedTypeInstance.create(description);
  }

  @Override
  public TypeInstance from(Object type) throws DiamondException, IllegalArgumentException {
    if(type == null){
      throw new IllegalArgumentException("There's no Diamond type instance for null");
    }
    return cache.reuseOrCreateRepresentationFor(type, () -> {
      final TypeDescription typeDescription = descriptor.describe(type);
      return fromDescription(typeDescription);
    });
  }

  @Override
  public TypeInstance from(ReferenceOf<?> typeReference) throws IllegalArgumentException {
    return from(typeReference.getReferencedAnnotatedType());
  }

  @Override
  public Nary<TypeInstance> from(Object[] nativeTypes) throws DiamondException, IllegalArgumentException {
    if(nativeTypes == null){
      throw new IllegalArgumentException("The native type array can't be null");
    }
    return Nary.from(nativeTypes)
      .map(this::from);
  }

  @Override
  public TypeInstance named(String typeName) throws DiamondException {
    Class<?> nativeType = null;
    try {
      nativeType = Class.forName(typeName);
    } catch (ExceptionInInitializerError e) {
      throw new DiamondException("An error in the initializationA of the class[" + typeName +
        "] prevented us from creating a type", e);
    } catch (LinkageError e) {
      throw new DiamondException("A linkage error for class[" + typeName + "] prevented us from creating a type", e);
    } catch (ClassNotFoundException e) {
      throw new DiamondException("The class[" + typeName + "] could not be found", e);
    }

    return from(nativeType);
  }

  @Override
  public TypeInstance fromParameterizedNativeTypes(Class<?> parameterizableSubtype,
                                                   List<TypeInstance> subtypeArguments,
                                                   AnnotatedType annotatedSuperType,
                                                   Type genericSupertype) {
    ExtendedTypeDescription extendedTypeDescription = describeExtension(
      parameterizableSubtype,
      subtypeArguments,
      annotatedSuperType,
      genericSupertype
    );
    return fromDescription(extendedTypeDescription);
  }

  private ExtendedTypeDescription describeExtension(Class<?> parameterizableSubtype,
                                                    List<TypeInstance> subtypeArguments,
                                                    AnnotatedType annotatedSuperType,
                                                    Type genericSupertype
  ) {
    TypeDescription supertypeDescription = descriptor.describe(annotatedSuperType);
    SupertypeParametrization parametrization = SupertypeParametrizationAnalyzer
      .create(parameterizableSubtype, genericSupertype)
      .get();
    ActualArgumentReplacer typeArgumentsReplacer = ActualArgumentReplacer.create(subtypeArguments, parametrization);
    final Class<?> extendedRawClass = RawClassesCalculator.create()
      .fromUnknown(genericSupertype)
      .unique().get(); // This should be safe if an interface type is used
    return ExtendedTypeDescription
      .create(supertypeDescription, typeArgumentsReplacer, extendedRawClass);
  }

  public static TypeSourceImpl create(DiamondCache cache) {
    TypeSourceImpl source = new TypeSourceImpl();
    source.cache = cache;
    source.descriptor = TypeDescriptor.create(); //Use default
    return source;
  }

}
