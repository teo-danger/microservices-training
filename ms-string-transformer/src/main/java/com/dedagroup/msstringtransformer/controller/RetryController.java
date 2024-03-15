package com.dedagroup.msstringtransformer.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/testRetry")
public class RetryController {

    private final Logger log = LoggerFactory.getLogger(RetryController.class);
    private final RestTemplate restTemplate;

    @Autowired
    public RetryController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    @Retry(name = "retry", fallbackMethod = "fallbackMethod")
    public String testRetry(){
        return restTemplate.getForObject("http://localhost:8010/unexisting-path", String.class);
    }

    public String fallbackMethod(Exception ex){
        log.error("EXCEPTION LOG: " + ex.getMessage());
        return "STRINGA DI DEFAULT ECCEZIONE";
    }

}
