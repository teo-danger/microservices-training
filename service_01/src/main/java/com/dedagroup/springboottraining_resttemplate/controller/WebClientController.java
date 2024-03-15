package com.dedagroup.springboottraining_resttemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/web-client")
public class WebClientController {

    @Autowired
    private WebClient webClient;

    private final String USER_API_URL = "http://localhost:8080/user/";
    private final String ACTIVE_USER = "flagAttivo/true";



    @GetMapping("/users/active")
    public ResponseEntity<List<String>> getAllActiveUsers(){

        List<Integer> activeUsersIdList = webClient.get().uri(USER_API_URL + ACTIVE_USER)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        List<String> response = new ArrayList<>();
        for(Integer id : activeUsersIdList) {
            String user = webClient.get().uri(USER_API_URL + id).retrieve().bodyToMono(String.class).block();
            if(user != null){
                response.add(user);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
