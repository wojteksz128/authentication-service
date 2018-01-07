package net.wojteksz128.authservice.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/**/css/**", "/**/js/**", "/webjars/**").permitAll()
                .antMatchers("/devApp").hasAnyRole("DEVELOPER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/signIn").permitAll()
                .and()
                .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user@t.t").password("user").roles("USER")
                .and()
                .withUser("dev@t.t").password("dev").roles("DEVELOPER");
    }
}
