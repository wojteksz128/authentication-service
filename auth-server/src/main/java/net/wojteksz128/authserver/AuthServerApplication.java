package net.wojteksz128.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ComponentScans({
    @ComponentScan("net.wojteksz128.authservice.service"),
    @ComponentScan("net.wojteksz128.authserver")
})
@PropertySources({@PropertySource("classpath:services.properties")})
@EntityScan("net.wojteksz128.authservice.service")
public class AuthServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AuthServerApplication.class);
    }
}
