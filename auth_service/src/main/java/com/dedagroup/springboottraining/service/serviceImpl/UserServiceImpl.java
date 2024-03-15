package com.dedagroup.springboottraining.service.serviceImpl;

import com.dedagroup.springboottraining.converter.UserMapper;
import com.dedagroup.springboottraining.domain.Group;
import com.dedagroup.springboottraining.domain.User;
import com.dedagroup.springboottraining.dto.UserDto;
import com.dedagroup.springboottraining.exception.InvalidParameterException;
import com.dedagroup.springboottraining.repository.UserRepository;
import com.dedagroup.springboottraining.service.GroupService;
import com.dedagroup.springboottraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private final GroupService groupService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, GroupService groupService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.groupService = groupService;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(entity -> userMapper.userToDto(entity, new UserDto())).collect(Collectors.toList());
    }

//    @Override
//    public List<UtenteDto> getUtenti() {
//        return userRepository.findAll().stream().map(entity -> userMapper.userToUtenteDto(entity, new UtenteDto())).collect(Collectors.toList());
//    }

    @Override
    public List<UserDto> findUserByFlagAttivoIs(boolean flag) {
        return userRepository.findUserByFlagAttivoIs(flag).stream().map(entity -> userMapper.userToDto(entity, new UserDto())).collect(Collectors.toList());
    }

    @Override
    public List<Long> findIdsByFlagAttivoIs(boolean flag) {
        return userRepository.findUserByFlagAttivoIs(flag).stream().map(entity -> userMapper.userToDto(entity, new UserDto())).map(UserDto::getId).collect(Collectors.toList());
    }

    @Override
    public UserDto findUserByEmail(String email){
        return userMapper.userToDto(userRepository.findUserByEmail(email)
                .orElseThrow(() -> new InvalidParameterException("No user found with email: " + email)), new UserDto());
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserDto createNewUser(UserDto userDto) {
        User newUser = new User();
        userMapper.dtoToUser(userDto, newUser);
        newUser = userRepository.save(newUser);
        return userMapper.userToDto(newUser, userDto);
    }


//    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = userById(id);
        userMapper.dtoToUser(userDto, user);
        user = userRepository.save(user);
        return userMapper.userToDto(user, userDto);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteUser(Long id) {
        User user = userById(id);
        userRepository.delete(user);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserDto activateUser(Long id) {
        User user = this.userById(id);
        if(!user.isFlagAttivo()) {
            user.setFlagAttivo(true);
        }
        user = userRepository.save(user);
        return userMapper.userToDto(user, new UserDto());
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserDto deactivateUser(Long id) {
        User user = this.userById(id);
        if(user.isFlagAttivo()) {
            user.setFlagAttivo(false);
        }
        user = userRepository.save(user);
        return userMapper.userToDto(user, new UserDto());
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserDto updateUserGroups(Long userId, List<Long> inputGroupIds) {
        User user = userById(userId);
        user.getGroups().clear();
        List<Group> groupToAdd = groupService.findGroupsByIdList(inputGroupIds);
        user.getGroups().addAll(groupToAdd);
        user = userRepository.save(user);
        return userMapper.userToDto(user, new UserDto());
    }


    public User userById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new InvalidParameterException("No user found with id: " + id));
    }

    public UserDto findUserById(Long id){
        return userMapper.userToDto(userRepository.findById(id)
                .orElseThrow(() -> new InvalidParameterException("No user found with id: " + id)), new UserDto());
    }

    public UserDto loadUserByUsername(String username){
        return userMapper.userToDto(userRepository.findUserByUsername(username)
                .orElseThrow(() -> new InvalidParameterException("No user found with username: " + username)), new UserDto());
    }


    public Collection<? extends GrantedAuthority> getAuthorities(UserDto user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getGroups()
                .stream()
                .map(Group::getRoles)
                .toList()
                .stream()
                .flatMap(Set::stream)
                .forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }


    @Override
    public UserDto registerUser(UserDto userDto) {
        User registeredUser = new User();
        userMapper.registrationUserWithAuthToken(userDto, registeredUser);
        registeredUser = userRepository.save(registeredUser);
        return userMapper.userToDto(registeredUser, userDto);
    }


    @Override
    public UserDto getAndCheckAuthToken(String authToken) {
        Optional<User> user = userRepository.findUserByActivationToken(authToken);
        if(user.isPresent()){
            return userMapper.userToDto(user.get(), new UserDto());
        } else {
            throw new InvalidParameterException("No user match with this activation token");
        }
    }

    @Override
    public void updatePasswordAndActivateUser(String password, Long id) {
        User u = userById(id);
            u.setFlagAttivo(true);
            u.setPassword(passwordEncoder.encode(password));
            u.setCheckPassword(passwordEncoder.encode(password));
            u.setActivationToken(null);
            userRepository.save(u);

    }

}