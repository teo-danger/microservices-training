package com.dedagroup.springboottraining_resttemplate.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "authService", url = "http://localhost:8080/")
public interface UserClient {


    @GetMapping(value = "/user/utenti")
    ResponseEntity<String> getAllUsers(@RequestParam("version") String version);

    @GetMapping(value = "/user/utenti")
    ResponseEntity<String> getUtenti(@RequestParam("version") String version);


    @GetMapping(value = "/user/{id}")
    ResponseEntity<String> getUserById(@PathVariable("id") Long id);

    @GetMapping(value = "/user/flagAttivo/{flag}")
    ResponseEntity<List<Long>> getUsersByFlag(@PathVariable("flag") boolean flag);

}
