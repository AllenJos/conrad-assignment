package de.conrad.codeworkshop.factory.services.factory;

import de.conrad.codeworkshop.factory.services.order.api.Order;
import de.conrad.codeworkshop.factory.services.order.api.OrderStatus;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Andreas Hartmann
 */
@org.springframework.stereotype.Service("factoryService")
public class Service {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private ConcurrentLinkedQueue<Order> manufacturingQueue = new ConcurrentLinkedQueue<>();

    /**
     * Method for adding order to the manufacturingQueue
     * @param order
     *        allowed object is:
     *        {@link Order}
     */
    void enqueue(final Order order) {
        order.setStatus(OrderStatus.IN_PROGRESS);
        manufacturingQueue.add(order);
    }

    /**
     * Method for returning the manufacturingQueue
     * @return
     *        objects of the following type(s) are allowed:
     *        {@link ConcurrentLinkedQueue}
     */
    public ConcurrentLinkedQueue<Order> getManufacturingQueue(){
        return this.manufacturingQueue;
    }
}
