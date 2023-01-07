package de.conrad.codeworkshop.factory;

import de.conrad.codeworkshop.factory.services.order.api.Order;
import de.conrad.codeworkshop.factory.services.order.api.Position;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FactoryApplicationTests {

    private static Order order;
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        order = new Order();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldVerifyProductIdNotBetween6and9digitsIsInvalid() {
        setOrder(12345, BigDecimal.valueOf(10));

        List<ConstraintViolation<Order>> validate = new ArrayList<>(validator.validate(order));

        assertEquals(1, validate.size());
        assertEquals("ProductId should be between 6 and 9 digits (including)",
                validate.get(0).getMessage());
    }

    @Test
    public void shouldVerifyProductIdBetween6and9digitsIsValid() {
        setOrder(123456, BigDecimal.valueOf(0.2));

        List<ConstraintViolation<Order>> validate = new ArrayList<>(validator.validate(order));

        assertEquals(0, validate.size());
    }

    @Test
    public void shouldVerifyQuantityNotDivisibleBy10isInvalid() {
        setOrder(123456, BigDecimal.valueOf(9));

        List<ConstraintViolation<Order>> validate = new ArrayList<>(validator.validate(order));

        assertEquals(1, validate.size());
        assertEquals("Product Quantity is not valid! " +
                        "Quantity should be: "+
                        " -divisible by 10 "+
                        " -or less than one and larger than 0 "+
                        " -or exactly 42.42.",
                validate.get(0).getMessage());
    }

    @Test
    public void shouldVerifyQuantityDivisibleBy10isValid() {
        setOrder(123456, BigDecimal.valueOf(10));

        List<ConstraintViolation<Order>> validate = new ArrayList<>(validator.validate(order));

        assertEquals(0, validate.size());
    }

    @Test
    public void shouldVerifyQuantityLessThanZeroIsInvalid() {
        setOrder(123456, BigDecimal.valueOf(-1));

        List<ConstraintViolation<Order>> validate = new ArrayList<>(validator.validate(order));

        assertEquals(1, validate.size());
        assertEquals("Product Quantity is not valid! " +
                        "Quantity should be: "+
                        " -divisible by 10 "+
                        " -or less than one and larger than 0 "+
                        " -or exactly 42.42.",
                validate.get(0).getMessage());
    }

    @Test
    public void shouldVerifyQuantityGreaterThanOneIsInvalid() {
        setOrder(123456, BigDecimal.valueOf(1.1));

        List<ConstraintViolation<Order>> validate = new ArrayList<>(validator.validate(order));

        assertEquals(1, validate.size());
        assertEquals("Product Quantity is not valid! " +
                        "Quantity should be: "+
                        " -divisible by 10 "+
                        " -or less than one and larger than 0 "+
                        " -or exactly 42.42.",
                validate.get(0).getMessage());
    }

    @Test
    public void shouldVerifyQuantityEqualToZeroIsInvalid() {
        setOrder(123456, BigDecimal.valueOf(0));

        List<ConstraintViolation<Order>> validate = new ArrayList<>(validator.validate(order));

        assertEquals(1, validate.size());
        assertEquals("Product Quantity is not valid! " +
                        "Quantity should be: "+
                        " -divisible by 10 "+
                        " -or less than one and larger than 0 "+
                        " -or exactly 42.42.",
                validate.get(0).getMessage());
    }

    @Test
    public void shouldVerifyQuantityEqualToOneIsInvalid() {
        setOrder(123456, BigDecimal.valueOf(1));

        List<ConstraintViolation<Order>> validate = new ArrayList<>(validator.validate(order));

        assertEquals(1, validate.size());
        assertEquals("Product Quantity is not valid! " +
                        "Quantity should be: "+
                        " -divisible by 10 "+
                        " -or less than one and larger than 0 "+
                        " -or exactly 42.42.",
                validate.get(0).getMessage());
    }


    @Test
    public void shouldVerifyQuantityBetweenZeroAndOneIsValid() {
        setOrder(123456, BigDecimal.valueOf(0.2));

        List<ConstraintViolation<Order>> validate = new ArrayList<>(validator.validate(order));

        assertEquals(0, validate.size());
    }

    @Test
    public void shouldVerifyQuantityNotEqualTo42_42IsInvalid() {
        setOrder(123456, BigDecimal.valueOf(42.3));

        List<ConstraintViolation<Order>> validate = new ArrayList<>(validator.validate(order));

        assertEquals(1, validate.size());
        assertEquals("Product Quantity is not valid! " +
                        "Quantity should be: "+
                        " -divisible by 10 "+
                        " -or less than one and larger than 0 "+
                        " -or exactly 42.42.",
                validate.get(0).getMessage());
    }

    @Test
    public void shouldVerifyQuantityEqualTo42_42IsValid() {
        setOrder(123456, BigDecimal.valueOf(42.42));

        List<ConstraintViolation<Order>> validate = new ArrayList<>(validator.validate(order));

        assertEquals(0, validate.size());
    }

    private void setOrder(int productId, BigDecimal quantity){
        List<Position> positionList = new ArrayList<>();
        Position position = new Position();
        position.setProductId(productId);
        position.setQuantity(quantity);
        positionList.add(position);
        order.setPositions(positionList);
    }
}
