package be.solidconsulting.entry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;

@RestController
public class MyRestController {

    @Autowired
    private RestTemplate restTemplate;

    @CrossOrigin(origins = CorsConfiguration.ALL)
    @GetMapping(value = "/entry")
    @PreAuthorize("hasRole('ENTRY')")
    public String get() {
        return "{ \"message\": \"Entrypoint Secured Met ROLE_ENTRY\"}";
    }

    @GetMapping(value = "/subservice")
    @PreAuthorize("hasRole('ENTRY')")
    public ResponseEntity<String>  getSecured() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://tvh.test:8082/subservice", String.class);
        return ResponseEntity.status(forEntity.getStatusCode()).body(forEntity.getBody());
    }
}
