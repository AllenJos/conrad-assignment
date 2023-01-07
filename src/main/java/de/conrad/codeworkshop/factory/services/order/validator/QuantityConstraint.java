package de.conrad.codeworkshop.factory.services.order.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom Annotation for Validating quantity attribute in {@link de.conrad.codeworkshop.factory.services.order.api.Position}
 */
@Constraint(validatedBy = QuantityValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuantityConstraint {
    String message() default "Product Quantity is not valid! " +
            "Quantity should be: "+
            " -divisible by 10 "+
            " -or less than one and larger than 0 "+
            " -or exactly 42.42.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
