package ar.com.kfgodel.diamond.testobjects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This type serves as test annotation
 * Created by kfgodel on 20/09/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.TYPE_USE
        })
public @interface TypeTestAnnotation {
}
