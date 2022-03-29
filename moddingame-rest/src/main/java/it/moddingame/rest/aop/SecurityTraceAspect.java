package it.moddingame.rest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;
import java.io.Serializable;



@Aspect
public class SecurityTraceAspect implements Serializable, Ordered {

    private static final long serialVersionUID = 1L;
    private final int order = 2;

    @Autowired
    private HttpSession session;

    @Pointcut(value = "execution(public * it.moddingame.rest.serviceimpl.*.*(..)) ")
    public void anyPublicServiceMethod() {
    }

    @Pointcut(value = "execution(public * it.moddingame.rest.controller.*.*(..))")
    public void anyPublicControllerMethod() {
    }

    public SecurityTraceAspect() {
        super();
    }

    @PreAuthorize("isAuthenticated()")
    private boolean checkGrantProfile(ProceedingJoinPoint pjp, SecurityTrace securityTrace) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return true;
    }

    private synchronized void traceMethod(ProceedingJoinPoint pjp, SecurityTrace securityTrace) throws Throwable {

        /*SSOAuthenticationToken auth = ((SSOAuthenticationToken) SecurityContextHolder.getContext().getAuthentication());
        String sessionId = auth.getSessionId();*/
        /*
         * Implementare qui la logica del trace aspect.
         */
    }

    @Override
    public int getOrder() {
        return order;
    }
}
