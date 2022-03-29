package it.moddingame.rest.config;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * Configurazione principale (Java) dell'applicazione. Include tutte le altre
 * configurazioni parziali.
 */
@Configuration
@Import(value = {WebApplicationConfig.class})
public class MainApplicationConfig {

    /**
     * Log applicativo.
     */
    private static final Log LOG = LogFactory.getLog(MainApplicationConfig.class);

    @Autowired
    @Resource
    private Environment env;

    /**
     * Application custom initialization code.
     * <p/>
     * Spring profiles can be configured with a system property
     * -Dspring.profiles.active=javaee
     * <p/>
     */
    @PostConstruct
    public void initApp() {
        LOG.info("Start Inizializzazione applicazione ...");
        LOG.debug("Looking for Spring profiles...");
        if (env.getActiveProfiles().length == 0) {
            LOG.info("No Spring profile configured, running with default configuration.");
        } else {
            for (String profile : env.getActiveProfiles()) {
                LOG.info("Detected Spring profile:" + profile);
            }
        }
        LOG.info("End Inizializzazione applicazione.");
    }
}
