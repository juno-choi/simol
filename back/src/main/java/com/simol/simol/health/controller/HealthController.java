package com.simol.simol.health.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<String> hello() {
        log.info("health check");
        return ResponseEntity.ok("ok");
    }
}
