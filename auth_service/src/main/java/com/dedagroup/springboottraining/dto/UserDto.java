package com.dedagroup.springboottraining.dto;

import com.dedagroup.springboottraining.annotation.CheckCredentials;
import com.dedagroup.springboottraining.annotation.ValidatePassword;
import com.dedagroup.springboottraining.domain.Group;
import com.dedagroup.springboottraining.domain.Role;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@CheckCredentials.List({
        @CheckCredentials(
                field = "password",
                fieldMatch = "checkPassword",
                message = "Passwords do not match!"
        ),
        @CheckCredentials(
                field = "email",
                fieldMatch = "checkEmail",
                message = "Email addresses do not match!"
        )
})
@JsonFilter("userFilter")
public class UserDto {

    private final String PASSWORD_TEMPORANEA = "CWoijp423qkmdpqi3n4o123";

    private Long id;

    private AnagraficaDto anagraficaDto;

    @NotNull
    @Size(max = 50)
    private String username;

    @NotNull
    @Size(max = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    private String lastName;


    private boolean flagAttivo;

    @Email
    @Size(max = 100)
    private String email;
    private String checkEmail;

    @NotNull
    @ValidatePassword
    private String password = PASSWORD_TEMPORANEA;

    private String checkPassword = PASSWORD_TEMPORANEA;

    private Set<Group> groups = new HashSet<>();

    private Set<Role> roles;



    private String activationToken;

}
