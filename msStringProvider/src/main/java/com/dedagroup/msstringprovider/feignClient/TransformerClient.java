package com.dedagroup.msstringprovider.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


//@FeignClient(value = "transformerService", url = "MS-STRING-TRANSFORMER")
@FeignClient("MS-STRING-TRANSFORMER")
public interface TransformerClient {

    @GetMapping(value = "/transform/uppercase")
    ResponseEntity<String> transformToUpperCase(@RequestParam("string") String string);

    @GetMapping("/transform/hi")
    ResponseEntity<String> hi();

}

