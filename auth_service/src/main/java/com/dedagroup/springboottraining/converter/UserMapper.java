package com.dedagroup.springboottraining.converter;

import com.dedagroup.springboottraining.domain.Group;
import com.dedagroup.springboottraining.domain.Role;
import com.dedagroup.springboottraining.domain.User;
import com.dedagroup.springboottraining.dto.AnagraficaDto;
import com.dedagroup.springboottraining.dto.UserDto;
import com.dedagroup.springboottraining.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto userToDto(User user, UserDto userDto){
        userDto.setAnagraficaDto(new AnagraficaDto());
        userDto.getAnagraficaDto().setNome(user.getFirstName());
        userDto.getAnagraficaDto().setCognome(user.getLastName());
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setFlagAttivo(user.isFlagAttivo());
        userDto.setPassword(user.getPassword());
        userDto.setCheckPassword(user.getCheckPassword());
        userDto.setGroups(user.getGroups());
        userDto.setRoles(getRoles(user));
        if(user.getActivationToken() != null){
            userDto.setActivationToken(user.getActivationToken());
        }
        return userDto;
    }
    public User dtoToUser(UserDto userDto, User user){
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setFlagAttivo(userDto.isFlagAttivo());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCheckPassword(passwordEncoder.encode(userDto.getCheckPassword()));
        user.setGroups(userDto.getGroups());
        return user;
    }


    public void registrationUserWithAuthToken(UserDto userDto, User user){
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setGroups(userDto.getGroups());
        user.setFlagAttivo(false);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCheckPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setActivationToken(Utilities.generateRandomAlphaNumeric(20));
    }



    private Set<Role> getRoles(User user){
        return user.getGroups()
                .stream()
                .map(Group::getRoles)
                .collect(Collectors.toSet())
                .stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }



//    public UtenteDto userToUtenteDto(User user, UtenteDto utenteDto){
//        utenteDto.setAnagraficaDto(new AnagraficaDto());
//        utenteDto.setId(user.getId());
//        utenteDto.setUsername(user.getUsername());
//        utenteDto.getAnagraficaDto().setNome(user.getFirstName());
//        utenteDto.getAnagraficaDto().setCognome(user.getLastName());
//        utenteDto.setEmail(user.getEmail());
//        utenteDto.setFlagAttivo(user.isFlagAttivo());
//        utenteDto.setPassword(user.getPassword());
//        utenteDto.setCheckPassword(user.getCheckPassword());
//        utenteDto.setGroups(user.getGroups());
//        utenteDto.setRoles(getRoles(user));
//        if(user.getActivationToken() != null){
//            utenteDto.setActivationToken(user.getActivationToken());
//        }
//        return utenteDto;
//    }
//
//
//    public User utenteDtoToUser(UtenteDto utenteDto, User user){
//        user.setId(utenteDto.getId());
//        user.setUsername(utenteDto.getUsername());
//        user.setFirstName(utenteDto.getAnagraficaDto().getNome());
//        user.setLastName(utenteDto.getAnagraficaDto().getCognome());
//        user.setEmail(utenteDto.getEmail());
//        user.setFlagAttivo(utenteDto.isFlagAttivo());
//        user.setPassword(passwordEncoder.encode(utenteDto.getPassword()));
//        user.setCheckPassword(passwordEncoder.encode(utenteDto.getCheckPassword()));
//        user.setGroups(utenteDto.getGroups());
//        return user;
//    }
}
