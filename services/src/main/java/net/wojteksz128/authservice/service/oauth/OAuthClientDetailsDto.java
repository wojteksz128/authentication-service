package net.wojteksz128.authservice.service.oauth;

import lombok.Data;

import java.util.List;

@Data
public class OAuthClientDetailsDto {

    private String clientId;
    private List<String> resourceIds;
    private String clientSecret;
    private List<String> scope;
    private List<String> authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private Boolean autoApprove;
}
