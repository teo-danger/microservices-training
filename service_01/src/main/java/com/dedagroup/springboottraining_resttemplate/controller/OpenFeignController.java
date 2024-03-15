package com.dedagroup.springboottraining_resttemplate.controller;
import com.dedagroup.springboottraining_resttemplate.feignClient.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class OpenFeignController {


    @Autowired
    private UserClient userClient;


    @GetMapping(path = "/user/utenti", params = "version=1")
    ResponseEntity<String> getAllUsers(@RequestParam("version") String version){
        return userClient.getAllUsers("1");
    };

    @GetMapping(path = "/user/utenti", params = "version=2")
    ResponseEntity<String> getUtenti(@RequestParam("version") String version){
        return userClient.getUtenti("2");
    };






//    @GetMapping(value = "/user/{id}")
//    public String getUserById(@PathVariable("id") Long id) {
//        System.out.println("sono nel controller");
//        return userClient.getUserById(id).getBody();
////        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//    @GetMapping(value = "/user/flagAttivo/{flag}")
//    public ResponseEntity<List<Long>> getUsersByFlag(@PathVariable("flag") boolean flag) {
//        return userClient.getUsersByFlag(flag);
////        return new ResponseEntity<>(HttpStatus.OK);
//    }


//    @GetMapping()
//    public ResponseEntity<List<String>> getAllActiveUsers(@PathVariable("flag") boolean flag, @PathVariable("id") Long id){
//        List<Long> activeUsersIdList = userClient.getUsersByFlag(flag).getBody();
//        List<String> response = new ArrayList<>();
//        for(Long userId : activeUsersIdList) {
//            String user = userClient.getUserById(id).getBody();
//            if(user != null){
//                response.add(user);
//            }
//        }
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
