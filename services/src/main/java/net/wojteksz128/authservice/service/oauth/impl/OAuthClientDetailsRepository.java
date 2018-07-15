package net.wojteksz128.authservice.service.oauth.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
interface OAuthClientDetailsRepository extends JpaRepository<OAuthClientDetails, String> {

    OAuthClientDetails findByClientId(String clientId);
}
