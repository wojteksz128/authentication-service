package net.wojteksz128.authservice.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<UserDto> findByEmail(String email);

    void save(UserRegistrationDto user);
}
