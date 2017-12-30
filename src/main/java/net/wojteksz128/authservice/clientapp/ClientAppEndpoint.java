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
    ResponseEntity<?> getAllApps();

    @RequestMapping(value = "/app", method = POST)
    ResponseEntity<?> addNewClientApp(@RequestBody ClientApp app);

    @RequestMapping(value = "/app/{id}", method = GET)
    ResponseEntity<?> getApp(@PathVariable("id") Long appId);

    @RequestMapping(value = "/app/{id}", method = PUT)
    ResponseEntity<?> updateAppInfo(@PathVariable("id") Long appId, @RequestBody ClientApp app);

    @RequestMapping(value = "/app/{id}", method = DELETE)
    ResponseEntity<?> deleteApp(@PathVariable("id") Long appId, @RequestBody ClientApp app);
}
