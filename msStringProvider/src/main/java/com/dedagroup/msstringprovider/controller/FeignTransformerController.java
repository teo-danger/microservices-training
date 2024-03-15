package com.dedagroup.msstringprovider.controller;

import com.dedagroup.msstringprovider.feignClient.TransformerClient;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/string")
public class FeignTransformerController {

    private final TransformerClient trCl;

    @Autowired
    public FeignTransformerController(TransformerClient trCl) {
        this.trCl = trCl;
    }

    @GetMapping(path = "/transform/uppercase")
    ResponseEntity<String> getAllUsers(@RequestParam("string") String string){
        return trCl.transformToUpperCase(string);
    };

    @GetMapping("/transform/hi")
    public ResponseEntity<String> hi(){
        return trCl.hi();
    }

}
