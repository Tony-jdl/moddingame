package it.moddingame.rest.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Log applicativo.
     */
    private static final Log LOG = LogFactory.getLog(CustomAuthenticationEntryPoint.class);

    private static final String ACTION_NOT_AUTHORIZED = "/notAuthorized";

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException {
        response.sendRedirect(request.getContextPath() + ACTION_NOT_AUTHORIZED);
    }

}
