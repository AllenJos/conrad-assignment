package de.conrad.codeworkshop.factory;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Andreas Hartmann
 */

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class FactoryApplication {
}
