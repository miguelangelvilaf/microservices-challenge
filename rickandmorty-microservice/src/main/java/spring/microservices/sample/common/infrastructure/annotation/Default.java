package spring.microservices.sample.common.infrastructure.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Default annotation for MapStruct to mark default constructor when there are multiple constructors
 *
 * @author Miguel A. Vila
 */
@Documented
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.CONSTRUCTOR })
public @interface Default {

    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";

}
