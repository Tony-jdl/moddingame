package it.moddingame.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@SpringBootApplication
public class MainApplication {

    /**
     * Log applicativo.
     */
    private static final Log LOG = LogFactory.getLog(MainApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(MainApplication.class, args);
        ConfigurableWebApplicationContext webApplicationContext = (ConfigurableWebApplicationContext) configurableApplicationContext;
        LOG.info("Application started at context path:" + webApplicationContext.getServletContext().getContextPath());

    }

}
