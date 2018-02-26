package net.wojteksz128.webclient.security;

import net.wojteksz128.authservice.service.user.UserDto;
import net.wojteksz128.authservice.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private static final Logger logger = Logger.getLogger(AccessDeniedHandlerImpl.class);

    private final UserService userService;

    @Autowired
    public AccessDeniedHandlerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        final UserDto currentLoggedUser = userService.getCurrentLoggedUser().orElseThrow(() -> new UsernameNotFoundException("Current logged user not found"));
        logger.warn(String.format("User: %s attempted to access the protected URL: %s",
            currentLoggedUser.getEmail(), request.getRequestURI()));

        response.sendRedirect(request.getContextPath() + "/403");
    }
}
