package net.wojteksz128.authservice.clientapp;

import net.wojteksz128.authservice.Repository;
import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotFoundException;

import java.util.List;

@SuppressWarnings("unused")
@org.springframework.stereotype.Repository
interface ClientAppRepository extends Repository<ClientApp> {

    ClientApp findByGuid(String guid) throws InvalidRequestException, ObjectNotFoundException;

    List<ClientApp> findByNameContains(String value);

    List<ClientApp> findAllApps();

    List<ClientApp> findByUserId(Long userId);
}
