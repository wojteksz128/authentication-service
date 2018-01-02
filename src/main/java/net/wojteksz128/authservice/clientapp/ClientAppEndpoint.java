package net.wojteksz128.authservice.clientapp;

import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/config/")
interface ClientAppEndpoint {

    @RequestMapping(value = "/apps", method = GET)
    ResponseEntity<?> getAllApps() throws Exception;

    @RequestMapping(value = "/app", method = POST)
    ResponseEntity<?> addNewClientApp(@RequestBody CreateClientAppDto app);

    @RequestMapping(value = "/app/{guid}", method = GET)
    ResponseEntity<?> getApp(@PathVariable("guid") String appGuid) throws InvalidRequestException, ObjectNotFoundException;

    @RequestMapping(value = "/app/{guid}", method = PUT)
    ResponseEntity<?> updateApp(@PathVariable("guid") String appGuid, @RequestBody ClientAppDto app);

    @RequestMapping(value = "/app/{guid}", method = DELETE)
    ResponseEntity<?> deleteApp(@PathVariable("guid") String appGuid, @RequestBody ClientAppDto app);
}
