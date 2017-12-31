package net.wojteksz128.authservice.clientapp;

import net.wojteksz128.authservice.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
interface ClientAppRepository extends Repository<ClientApp> {

    Optional<ClientApp> findByGuid(String guid);

    List<ClientApp> findByNameContains(String value);

    List<ClientApp> findAllApps();
}
