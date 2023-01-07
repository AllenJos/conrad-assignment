package de.conrad.codeworkshop.factory.config;

import de.conrad.codeworkshop.factory.services.order.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Java class for running Scheduled task.
 */
@EnableAsync
@EnableScheduling
@Configuration
public class SchedulerConfig {

    @Autowired
    Service orderService;

    /**
     * Scheduled task running Asynchronously every 2 seconds.
     */
    @Async
    @Scheduled(cron = "*/2 * * * * *")
    public void startManufacturingOrders(){
        orderService.completeOrder();
    }

}
