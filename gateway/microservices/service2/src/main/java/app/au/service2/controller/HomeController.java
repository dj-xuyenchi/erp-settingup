package app.au.service2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @GetMapping("/test")
    public ResponseEntity<?> home1() {
        String images = restTemplate.getForObject("http://service1/api", String.class) + "-" + env.getProperty("local.server.port");

        return ResponseEntity.ok(images);
    }


}
