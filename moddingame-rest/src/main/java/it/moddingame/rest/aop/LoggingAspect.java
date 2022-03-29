package it.moddingame.rest.aop;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

/**
 *
 * @author pb
 */
@Aspect
public class LoggingAspect implements Serializable, Ordered {

    /**
     * Log applicativo.
     */
    private static final Log LOG = LogFactory.getLog(LoggingAspect.class);


    @Pointcut(value = "execution(public * it.moddingame.rest.serviceimpl.*.*(..))")
    public void serviceMethodsToBeProfiled() {
    }

    @Pointcut(value = "execution(public * it.moddingame.rest.controller.*.*(..))")
    public void controllerMethodsToBeProfiled() {
    }

    @After("controllerMethodsToBeProfiled()")
    public void logControllerMethod(JoinPoint joinPoint) {
        LOG.info("************************************************************");
        LOG.info("logControllerMethod method name: " + joinPoint.getSignature().toShortString());
        LOG.info("logControllerMethod method args : " + Arrays.toString(joinPoint.getArgs()));
        LOG.info("************************************************************");
    }

    @After("serviceMethodsToBeProfiled()")
    public void logServiceMethod(JoinPoint joinPoint) throws Throwable {
        LOG.info("************************************************************");
        LOG.info("logServiceMethod method name: " + joinPoint.getSignature().toShortString());
        LOG.info("logServiceMethod method args : " + Arrays.toString(joinPoint.getArgs()));
        LOG.info("************************************************************");
    }


   @Override
    public int getOrder() {
        return 0;
    }
}
