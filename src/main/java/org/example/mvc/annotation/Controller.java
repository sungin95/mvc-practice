package org.example.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // 클래스에 붙일 수 있게 만들기 위해.
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    String value() default "";

    String path() default "";
}
