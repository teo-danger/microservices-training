package com.dedagroup.msclient.controller;


import com.dedagroup.msclient.feignClient.StringClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class StringProviderController {

    private final StringClient stringClient;

    @Autowired
    public StringProviderController(StringClient stringClient) {
        this.stringClient = stringClient;
    }

    @GetMapping("/string-provider/provideInfo")
    public String getInfo(){
        return stringClient.getInfo();
    }

}
