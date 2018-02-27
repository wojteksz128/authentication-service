package net.wojteksz128.authserver.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

@RestController
class LogoutEndpoint {

    private final ConsumerTokenServices tokenServices;

    @Autowired
    public LogoutEndpoint(ConsumerTokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/oauth/revoke")
    @ResponseStatus
    public HttpStatus revokeToken(@RequestParam("token") String tokenId) {
        tokenServices.revokeToken(tokenId);
        return HttpStatus.OK;
    }
}
