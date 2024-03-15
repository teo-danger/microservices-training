package com.dedagroup.msstringprovider.controller;

import com.dedagroup.msstringprovider.properties.Info;
import com.dedagroup.msstringprovider.properties.Prop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/string-provider")
@RefreshScope
public class MsStringProviderController {

    private final Prop prop;
    private final Info info;


    @Autowired
    public MsStringProviderController(Prop prop, Info info) {
        this.prop = prop;
        this.info = info;
    }


    @GetMapping("")
    public String getAppName(){
        return prop.getNumber().getNumberone() + " " + prop.getNumber().getNumbertwo() + " " + prop.getServerInfo().getServerPort();
    }

    @GetMapping("/provideInfo")
    public ResponseEntity<String> getInfo(){

        String result = "App instance: " + info.getInstance() + "// App Server Port: " + info.getServerPort();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
