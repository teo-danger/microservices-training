package com.dedagroup.msstringtransformer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transform")
public class TransformerController {

    @GetMapping("/uppercase")
    public ResponseEntity<String> transformToUpperCase(@RequestParam("string") String string){
        return new ResponseEntity<>(string.toUpperCase(), HttpStatus.OK);
    }

    @GetMapping("/hi")
    public ResponseEntity<String> hi(){
        return new ResponseEntity<>("hi", HttpStatus.OK);
    }
}
