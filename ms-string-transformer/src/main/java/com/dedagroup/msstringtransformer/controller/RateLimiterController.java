package com.dedagroup.msstringtransformer.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/rateLimiter")
public class RateLimiterController {

    private static final Logger log = Logger.getLogger(RateLimiterController.class.getSimpleName());


    @GetMapping
    @RateLimiter(name = "stringRateLimiter", fallbackMethod = "fallRateLimiter")
    @Bulkhead(name = "stringRateLimiter")
    public String getString(){
        return "rate limiter controller STRING";
    }

    public String fallRateLimiter(Exception ex){
        return "Il servizio non permette ulteriori chiamate";
    }

}
