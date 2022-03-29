package it.moddingame.rest.security;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Log applicativo.
     */
    private static final Log LOG = LogFactory.getLog(UrlAuthenticationSuccessHandler.class);

    protected Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private static final String USER_HOME = "/user";
    private static final String REVISORE_HOME = "/revisore";
    private static final String ADMIN_HOME = "/admin";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Builds the target URL according to the logic defined in the main class
     * Javadoc.
     *
     * @param authentication
     * @return
     */
    protected String determineTargetUrl(Authentication authentication) {
        boolean isProponente = false;
        boolean isRevisore = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_PROPONENTE")) {
                isProponente = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_REVISORE")) {
                isRevisore = true;
                break;
            }
        }

        if (isProponente) {
            return USER_HOME;
        } else if (isAdmin) {
            return ADMIN_HOME;
        } else if (isRevisore) {
            return REVISORE_HOME;
        } else {
            throw new IllegalStateException();
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
