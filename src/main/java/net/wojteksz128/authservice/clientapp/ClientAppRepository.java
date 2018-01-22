package net.wojteksz128.authservice.clientapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SuppressWarnings("unused")
interface ClientAppRepository extends JpaRepository<ClientApp, Long> {

    ClientApp findByGuid(String guid);

    List<ClientApp> findAllByNameContains(String name);

    List<ClientApp> findAllByUserId(Long id);
}
