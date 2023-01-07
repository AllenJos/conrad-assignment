package de.conrad.codeworkshop.factory.services.order;

import de.conrad.codeworkshop.factory.services.order.api.Order;
import de.conrad.codeworkshop.factory.services.order.api.OrderConfirmation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Andreas Hartmann
 */
@RestController("orderController")
@RequestMapping(value = "/order")
public class Controller {

    private final Service factoryService;

    @Autowired
    public Controller(final Service service) {
        this.factoryService = service;
    }

    @ApiOperation("Creates New Order.")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
                                    produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderConfirmation createOrder(@Valid @RequestBody final Order order) {
        return factoryService.createOrder(order);
    }
}