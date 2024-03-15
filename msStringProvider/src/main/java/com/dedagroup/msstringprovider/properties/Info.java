package com.dedagroup.msstringprovider.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Info {

    @Value("${prop.instance}")
    private String instance;

    @Value("${server.port}")
    private String serverPort;

}
