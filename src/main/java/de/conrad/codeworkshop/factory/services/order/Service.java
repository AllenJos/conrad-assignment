package de.conrad.codeworkshop.factory.services.order;

import de.conrad.codeworkshop.factory.services.factory.Controller;
import de.conrad.codeworkshop.factory.services.order.api.Order;
import de.conrad.codeworkshop.factory.services.order.api.OrderConfirmation;
import de.conrad.codeworkshop.factory.services.order.api.OrderNumber;
import de.conrad.codeworkshop.factory.services.order.api.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

import static de.conrad.codeworkshop.factory.services.order.api.OrderConfirmation.BLANK_ORDER_CONFIRMATION;
import static de.conrad.codeworkshop.factory.services.order.api.OrderStatus.ACCEPTED;

/**
 * @author Andreas Hartmann
 *
 * <p>Java class for processing orders
 *
 */
@org.springframework.stereotype.Service("orderService")
public class Service {

    private static final Logger log = LoggerFactory.getLogger(Service.class);

    private final Controller factoryController;

    @Autowired
    de.conrad.codeworkshop.factory.services.factory.Service factoryService;

    @Autowired
    de.conrad.codeworkshop.factory.services.notification.Service notificationService;

    private ConcurrentLinkedQueue<Order> orderConcurrentLinkedQueue;

    @Autowired
    public Service(de.conrad.codeworkshop.factory.services.factory.Controller factoryController) {
        this.factoryController = factoryController;
    }

    /**
     * Validates the input order. If it is valid, it is enqueued in the factory via the factoryController. Either way an
     * appropriate order confirmation is returned.
     * @param order
     *        allowed object is:
     *        {@link Order}
     *
     * @return
     *        Objects of the following type(s) are allowed:
     *        {@link OrderConfirmation}
     */

    public OrderConfirmation createOrder(final Order order) {
        order.validate();


        final OrderConfirmation orderConfirmation;

        if (order.getStatus() == ACCEPTED) {
            orderConfirmation = new OrderConfirmation(new OrderNumber(BigInteger.valueOf(new Random().nextInt())));

            order.setOrderConfirmation(orderConfirmation);

            factoryController.enqueue(order);
        } else {
            orderConfirmation = BLANK_ORDER_CONFIRMATION;
        }

        return orderConfirmation;
    }

    /**
     * <p>Method for removing orders from manufacturingQueue in {@link de.conrad.codeworkshop.factory.services.factory.Service},
     * <p>Sets the {@link OrderStatus} to COMPLETED and
     * <p>Calls the notifyCustomer method in {@link de.conrad.codeworkshop.factory.services.notification.Service}</p>
     */

    public void completeOrder() {

        this.orderConcurrentLinkedQueue = factoryService.getManufacturingQueue();
        CompletableFuture.runAsync(new Runnable() {
                                       @Override
                                       public void run() {
                                               while (!orderConcurrentLinkedQueue.isEmpty()){
                                                   Order order = orderConcurrentLinkedQueue.poll();
                                                   if(order!=null){
                                                       order.setStatus(OrderStatus.COMPLETED);
                                                       log.info("Complete Order: "+Thread.currentThread().getName());
                                                       notificationService.notifyCustomer(order);
                                                   }
                                               }
                                           }
                                   }
        ).join();
    }
}
