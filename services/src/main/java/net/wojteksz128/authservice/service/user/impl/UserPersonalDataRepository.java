package net.wojteksz128.authservice.service.user.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserPersonalDataRepository extends JpaRepository<UserPersonalData, Long> {

    Optional<UserPersonalData> findByUser(User user);

    Optional<UserPersonalData> findByUserLogin(String user_login);

    Optional<UserPersonalData> findById(Long id);
}
