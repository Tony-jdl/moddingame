package it.moddingame.rest.config;

import it.moddingame.rest.security.UrlAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * @param
     * @throws Exception
     */

    /* Scommentare solo se si utilizza l'autenticazione su DB
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/actuator/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
//                .httpBasic()
//                .and()
                .authorizeRequests(authz -> authz
                        .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/**").permitAll()
                        .antMatchers(HttpMethod.PUT, "/api/**").permitAll()
                        .antMatchers(HttpMethod.DELETE, "/api/**").permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        .anyRequest().authenticated())
                .csrf().disable();
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        AuthenticationSuccessHandler auth = new UrlAuthenticationSuccessHandler();
        return auth;
    }


    /*@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
    }*/
}
