package net.wojteksz128.authservice.service.user.impl;

import net.wojteksz128.authservice.service.user.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, RoleRepository.class})
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserToDtoConverter userToDtoConverter;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserToDtoConverter userToDtoConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userToDtoConverter = userToDtoConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userToDtoConverter::convert);
    }

    @Override
    public void save(UserRegistrationDto userDto) {
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(roleRepository.findByCode("USER").map(v -> new HashSet<>(Collections.singletonList(v))).orElse(null));

        userRepository.save(user);
    }

    @Override
    public void addRole(UserDto userDto, RoleDto roleDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new ObjectNotFoundException(userDto.getId(), "User"));
        Role role = roleRepository.findByCode(roleDto.getCode()).orElseThrow(() -> new ObjectNotFoundException(roleDto.getCode(), "Role"));
        user.getRoles().add(role);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        return new UserDetailsImpl(optionalUser
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email '%s' not exists", username))));
    }
}
