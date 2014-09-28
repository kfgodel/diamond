package ar.com.kfgodel.diamond.impl.types.parts;

import ar.com.kfgodel.diamond.api.sources.TypeNames;

import java.lang.annotation.Annotation;

/**
 * This type represents the common parts needed to build a TypeInstance
 * Created by kfgodel on 28/09/14.
 */
public interface CommonTypeParts {

    TypeNames getNames();

    Annotation[] getAnnotations();
}
