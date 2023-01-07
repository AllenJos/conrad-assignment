package de.conrad.codeworkshop.factory.services.order.api;

import de.conrad.codeworkshop.factory.services.order.validator.QuantityConstraint;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * @author Andreas Hartmann
 */
public class Position {

    @Range(min = 100000, max = 999999999, message = "ProductId should be between 6 and 9 digits (including)")
    private Integer productId;

    @QuantityConstraint
    private BigDecimal quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
