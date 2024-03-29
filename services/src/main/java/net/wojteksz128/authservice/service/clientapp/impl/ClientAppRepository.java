package net.wojteksz128.authservice.service.clientapp.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unused")
@Repository
interface ClientAppRepository extends JpaRepository<ClientApp, Long> {

    ClientApp findByClientId(String clientId);

    List<ClientApp> findAllByClientId(String clientId);

    List<ClientApp> findAllByUserId(Long id);
}
