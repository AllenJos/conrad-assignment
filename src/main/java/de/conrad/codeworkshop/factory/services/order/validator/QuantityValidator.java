package de.conrad.codeworkshop.factory.services.order.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class QuantityValidator implements ConstraintValidator<QuantityConstraint, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal quantity, ConstraintValidatorContext context) {
        return (quantity!=null) &&
                ((quantity.compareTo(BigDecimal.ZERO) !=0 &&
                quantity.divide(new BigDecimal("10")).stripTrailingZeros().scale() == 0) ||
                (quantity.compareTo(BigDecimal.ZERO) > 0 && quantity.compareTo(BigDecimal.ONE) < 0) ||
                (quantity.compareTo(new BigDecimal("42.42")) == 0));
    }
}
