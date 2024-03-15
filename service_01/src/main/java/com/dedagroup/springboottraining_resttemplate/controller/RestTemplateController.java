package com.dedagroup.springboottraining_resttemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest-template")
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_API_URL = "http://localhost:8080/user/";
    private final String ACTIVE_USER = "flagAttivo/true";



    @GetMapping("/users/active")
    public ResponseEntity<List<String>> getAllActiveUsers(){
        List<Integer> activeUsersIdList = restTemplate.getForObject(USER_API_URL + ACTIVE_USER, List.class);
        List<String> response = new ArrayList<>();
        for(Integer id : activeUsersIdList) {
            String user = restTemplate.getForObject(USER_API_URL + id, String.class);
            if(user != null){
                response.add(user);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
