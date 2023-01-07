package de.conrad.codeworkshop.factory.services.notification;

import de.conrad.codeworkshop.factory.services.order.api.Order;

/**
 * @author Andreas Hartmann
 */
@org.springframework.stereotype.Service("notificationService")
public class Service {

    /**
     * Method for sending notification to the customer.
     * @param order
     *        allowed objects are:
     *        {@link Order}
     */
    public void notifyCustomer(final Order order) {
        // Dummy function that would notify the customer that manufacturing is completed.

        try {
            Thread.sleep(5000);
            System.out.println("Sent email to customer parallely!");
        } catch (final InterruptedException interruptedException) {
            System.err.println(interruptedException.getMessage());
            // Do nothing
        }
    }
}
