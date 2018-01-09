package net.wojteksz128.authservice.user;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> findByEmail(String email);

    void save(UserRegistrationDto user);
}
