package net.wojteksz128.authservice.service.clientapp.impl;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "oauth_client_details")
class OAuthClientDetails implements Serializable {

    @Id
    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "resource_ids")
    @Convert(converter = StringListConverter.class)
    private List<String> resourceIds;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "scope")
    @Convert(converter = StringListConverter.class)
    private List<String> scope;

    @Column(name = "authorized_grant_types")
    @Convert(converter = StringListConverter.class)
    private List<String> authorizedGrantTypes;

    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(name = "additional_information")
    private String additionalInformation;

    @Column(name = "autoapprove")
    private Boolean autoApprove;
}
