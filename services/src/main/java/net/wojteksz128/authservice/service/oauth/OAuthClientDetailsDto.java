package net.wojteksz128.authservice.service.oauth;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
public class OAuthClientDetailsDto {

    @NotEmpty
    private String clientId;

    private List<String> resourceIds;

    @NotEmpty
    private String clientSecret;

    @NotEmpty
    private List<String> scope;

    @NotEmpty
    private List<String> authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private Boolean autoApprove;
}
