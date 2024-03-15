package com.dedagroup.springboottraining.controller;

import com.dedagroup.springboottraining.dto.UserDto;
import com.dedagroup.springboottraining.securityConfig.CustomAuthProvider;
import com.dedagroup.springboottraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class AuthController {

    private CustomAuthProvider authProvider;

    @Autowired
    private UserService userService;

    public AuthController(CustomAuthProvider authProvider){
        this.authProvider = authProvider;
    }

    @GetMapping("/home")
    public String showHome(){
        return "home";
    }


    @ModelAttribute
    @GetMapping("/login")
    public String loginProc(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "loginError";
        }
        model.addAttribute("login");
        return "redirect:/user/all/view";
    }



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/newUser")
    public String showNewUser(Model model){
        model.addAttribute("newUser", new UserDto());
        return "newUser";
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new UserDto());
        return "registration";
    }

//    @GetMapping("/registration/password")
//    public String showRegistrationPasswordPage(Model model){
//        return "password_registration";
//    }



}
