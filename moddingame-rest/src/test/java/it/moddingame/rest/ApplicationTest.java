package it.moddingame.rest;

import it.moddingame.rest.exception.ServiceException;
import it.moddingame.rest.risorse.RisorseService;
import it.moddingame.rest.risorse.dto.RisorsaDTO;

import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by p.b on 21/05/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@CommonsLog
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class ApplicationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    RisorseService risorseService;

    @Test
    @Order(1)
    @DisplayName("Elenco risorse")
    public void testListaRisorse() throws ServiceException {
        List<RisorsaDTO> risorse = risorseService.elenco();
        log.info("Numero Risorse:" + risorse.size());
        Assert.assertNotNull(risorse);
    }
}

