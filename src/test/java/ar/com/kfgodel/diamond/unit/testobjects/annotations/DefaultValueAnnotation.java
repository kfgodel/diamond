package ar.com.kfgodel.diamond.unit.testobjects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This type serves as a test object for default values in methods
 * Created by kfgodel on 08/11/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
  ElementType.METHOD
})
public @interface DefaultValueAnnotation {

  int memberWithDefault() default 23;

  int memberWithoutDefault();
}
