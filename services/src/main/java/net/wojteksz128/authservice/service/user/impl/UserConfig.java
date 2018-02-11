package net.wojteksz128.authservice.service.user.impl;

import net.wojteksz128.authservice.service.user.RoleFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
class UserConfig {

    private final RoleRepository roleRepository;

    @Autowired
    UserConfig(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Bean
    RoleFinder roleFinder() {
        return new RoleFinderImpl(roleRepository, roleToDtoConverter());
    }

    @Bean
    RoleToDtoConverter roleToDtoConverter() {
        return new RoleToDtoConverter();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {

            private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            @Override
            public String encode(CharSequence rawPassword) {
                return passwordEncoder.encode(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return passwordEncoder.matches(rawPassword, encodedPassword);
            }
        };
    }

    @Bean
    UserToDtoConverter userToDtoConverter() {
        return new UserToDtoConverter(roleToDtoConverter());
    }
}
