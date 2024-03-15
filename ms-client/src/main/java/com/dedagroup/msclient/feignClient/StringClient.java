package com.dedagroup.msclient.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("MS-STRING-PROVIDER")
public interface StringClient {

    @GetMapping("/string-provider/provideInfo")
    String getInfo();

}
