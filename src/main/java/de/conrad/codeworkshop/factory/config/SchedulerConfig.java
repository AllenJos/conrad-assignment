package de.conrad.codeworkshop.factory.config;

import de.conrad.codeworkshop.factory.services.order.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class SchedulerConfig {

    @Autowired
    Service orderService;

    @Async
    @Scheduled(cron = "*/2 * * * * *")
    public void startManufacturingOrders(){
        orderService.completeOrder();
    }

}
