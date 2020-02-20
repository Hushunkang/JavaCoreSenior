package javacore.senior.practice;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author hskbeginner
 * @create 2020/2/17-19:46
 * @see java.lang.reflect.AnnotatedElement
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Inherited
public @interface MyAnnotationHSK {

    String value() default "Hello~~";

}
