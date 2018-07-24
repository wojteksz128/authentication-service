package net.wojteksz128.authservice.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<UserDto> findByLogin(String email);

    void save(UserRegistrationDto user);

    void addRole(UserDto user, RoleDto role);

    Optional<UserDto> getCurrentLoggedUser();
}
