package com.dedagroup.springboottraining.service;

import com.dedagroup.springboottraining.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

//    List<UtenteDto> getUtenti();

    List<UserDto> findUserByFlagAttivoIs(boolean flag);

    List<Long> findIdsByFlagAttivoIs(boolean flag);

    UserDto findUserByEmail(String email);

    UserDto findUserById(Long id);

    UserDto createNewUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long id);

    void deleteUser(Long id);

    UserDto activateUser(Long id);

    UserDto deactivateUser(Long id);

    UserDto updateUserGroups(Long userId, List<Long> groups);

    UserDto loadUserByUsername(String username);

    Collection<? extends GrantedAuthority> getAuthorities(UserDto userDto);

    UserDto registerUser(UserDto userDto);

    UserDto getAndCheckAuthToken(String authToken);

    void updatePasswordAndActivateUser(String password, Long id);
}
