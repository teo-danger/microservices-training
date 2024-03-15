package com.dedagroup.msstringprovider.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "prop")
@Component
@Getter
@Setter
public class Number {

    private String numberone;
    private String numbertwo;

}
