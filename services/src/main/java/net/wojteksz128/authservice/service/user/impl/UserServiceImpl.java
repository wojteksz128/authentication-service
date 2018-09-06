package net.wojteksz128.authservice.service.user.impl;

import net.wojteksz128.authservice.service.user.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, RoleRepository.class})
// TODO: 23.07.2018 Zmień implementację (wywołuj nowy kontroler, a nie bezpośrednio repozytoria
class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserToDtoConverter userToDtoConverter;
    private final UserPersonalDataDtoToEntityConverter userPersonalDataDtoToEntityConverter;
    private final UserPersonalDataRepository userPersonalDataRepository;
    private final UserPersonalDataToDtoConverter userPersonalDataToDtoConverter;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserToDtoConverter userToDtoConverter, PasswordEncoder passwordEncoder, UserPersonalDataDtoToEntityConverter userPersonalDataDtoToEntityConverter, UserPersonalDataRepository userPersonalDataRepository, UserPersonalDataToDtoConverter userPersonalDataToDtoConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userToDtoConverter = userToDtoConverter;
        this.passwordEncoder = passwordEncoder;
        this.userPersonalDataDtoToEntityConverter = userPersonalDataDtoToEntityConverter;
        this.userPersonalDataRepository = userPersonalDataRepository;
        this.userPersonalDataToDtoConverter = userPersonalDataToDtoConverter;
    }

    @Override
    public Optional<UserDto> findByLogin(String login) {
        return userRepository.findByLogin(login).map(userToDtoConverter::convert);
    }

    @Override
    public void save(UserRegistrationDto userDto) {
        User user = new User();

        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(roleRepository.findByCode("USER").map(v -> new HashSet<>(Collections.singletonList(v))).orElse(null));
        user.setPersonalData(this.userPersonalDataDtoToEntityConverter.convert(userDto.getPersonalData()));
        user.getPersonalData().setUser(user);

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
    public Optional<UserDto> getCurrentLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserDto> currentLoggedUser = Optional.empty();

        if (authentication instanceof OAuth2Authentication) {

            final Object principal = authentication.getPrincipal();
            String login = null;
            if (principal instanceof String) {
                login = (String) principal;
            } else if (principal instanceof UserDetailsImpl) {
                login = ((UserDetailsImpl) principal).getUsername();
            }
            currentLoggedUser = userRepository.findByLogin(login).map(userToDtoConverter::convert);
        }

        return currentLoggedUser;
    }

    @Override
    public UserPersonalDataDto updatePersonalData(String login, UserPersonalDataDto personalData) {
        final Optional<UserDto> optionalUserDto = findByLogin(login);

        return optionalUserDto.map(UserDto::getPersonalData).map(userPersonalDataDtoToEntityConverter::convert).map(oldPersonalData -> {
            oldPersonalData.setBirthDate(personalData.getBirthDate());
            oldPersonalData.setEmail(personalData.getEmail());
            oldPersonalData.setFirstName(personalData.getFirstName());
            oldPersonalData.setLastName(personalData.getLastName());
            oldPersonalData.setPhoneNumber(personalData.getPhoneNumber());
            oldPersonalData.setUrl(personalData.getUrl());

            return userPersonalDataRepository.save(oldPersonalData);
        }).map(userPersonalDataToDtoConverter::convert).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByLogin(username);
        return new UserDetailsImpl(optionalUser
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User with login '%s' not exists", username))));
    }
}
