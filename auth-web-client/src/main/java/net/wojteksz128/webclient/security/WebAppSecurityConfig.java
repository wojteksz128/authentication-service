package net.wojteksz128.webclient.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    public WebAppSecurityConfig(AccessDeniedHandler accessDeniedHandler, LogoutSuccessHandler logoutSuccessHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests()
            .antMatchers("/", "/login**").permitAll()
            .anyRequest().authenticated()
            .and().logout().logoutSuccessHandler(logoutSuccessHandler).logoutSuccessUrl("/?logout").clearAuthentication(true).deleteCookies().permitAll()
            .and().exceptionHandling().accessDeniedPage("/403").accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/**/css/**", "/**/js/**", "/webjars/**");
    }
}
