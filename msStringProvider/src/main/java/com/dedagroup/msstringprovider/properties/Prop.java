package com.dedagroup.msstringprovider.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "prop")
@ConfigurationPropertiesScan
@Component
@Getter
@Setter
public class Prop {

    private Number number;
    private ServerInfo serverInfo;

}
