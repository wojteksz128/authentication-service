package net.wojteksz128.authservice.clientapp;

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
    ResponseEntity<?> addNewClientApp(@RequestBody ClientApp app);

    @RequestMapping(value = "/app/{guid}", method = GET)
    ResponseEntity<?> getApp(@PathVariable("guid") String appGuid);

    @RequestMapping(value = "/app/{guid}", method = PUT)
    ResponseEntity<?> updateApp(@PathVariable("guid") String appGuid, @RequestBody ClientApp app);

    @RequestMapping(value = "/app/{guid}", method = DELETE)
    ResponseEntity<?> deleteApp(@PathVariable("guid") String appGuid, @RequestBody ClientApp app);
}
