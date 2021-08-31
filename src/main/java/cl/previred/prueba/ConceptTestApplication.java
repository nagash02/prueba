package cl.previred.prueba;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import cl.previred.prueba.config.GlobalConfiguration;
import cl.previred.prueba.config.SecurityConfiguration;
import cl.previred.prueba.config.SwaggerConfiguration;

@SpringBootApplication
@EnableFeignClients
public class ConceptTestApplication {

    public static void main(String[] args) {
	
	Logger logger = LogManager.getLogger(ConceptTestApplication.class);

	Class<?>[] configClasses = { GlobalConfiguration.class,
		SecurityConfiguration.class, SwaggerConfiguration.class };

	SpringApplication.run(configClasses, args);

	logger.info("Hello, I'm Api :) ");

    }
}
