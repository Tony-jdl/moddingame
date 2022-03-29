package it.moddingame.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.moddingame.rest.aop.LoggingAspect;
import it.moddingame.rest.aop.SecurityTraceAspect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

import static it.moddingame.rest.util.DateHelper.DATE_FORMAT_IT_SLASH;

/**
 * Configurazione WEB (Java) dell'applicazione.
 */
@Configuration
@Import({SecurityConfig.class, LocalizationConfiguration.class})
public class WebApplicationConfig extends WebMvcConfigurationSupport {

    /**
     * Log applicativo.
     */
    private static final Log LOG = LogFactory.getLog(WebApplicationConfig.class);

    @Autowired
    @Resource
    private Environment env;

    @Bean
    public SecurityTraceAspect securityTraceAspect() {
        return new SecurityTraceAspect();
    }

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean(name = "filterMultipartResolver")
    public CommonsMultipartResolver filterMultipartResolver() {
        CommonsMultipartResolver filterMultipartResolver = new CommonsMultipartResolver();
        filterMultipartResolver.setDefaultEncoding("utf-8");
        // resolver.setMaxUploadSize(512000);
        return filterMultipartResolver;
    }


    @Bean
    public RestTemplate restTemplate() {
        // timeout
        final RestTemplate template = new RestTemplate(clientHttpRequestFactory());
        // utf8
        template.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return template;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        final String dummy = this.env.getProperty("timeout.restTemplate", "30");
        final int timeout = Integer.parseInt(dummy);
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(timeout * 1000);
        factory.setConnectTimeout(timeout * 1000);
        return factory;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/");
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT_IT_SLASH));
        return mapper;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }


    @Bean
    public WebMvcConfigurer customConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                configurer.defaultContentType(MediaType.APPLICATION_JSON);
            }
        };
    }


    /**
     * Eseguito allo startup dell'applicazione.
     */
    @PostConstruct
    public void onApplicationStartup() {
        LOG.info("***************************************");
        LOG.info("On Application startup called!!!");
        LOG.info("***************************************");
    }

    /**
     * Eseguito allo shutdown dell'applicazione.
     */
    @PreDestroy
    public void onApplicationExit() {
        LOG.info("***************************************");
        LOG.info("On Application exit called!!!");
        LOG.info("***************************************");
    }

}
