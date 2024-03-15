package com.dedagroup.springboottraining.controller;

import com.dedagroup.springboottraining.converter.UserMapper;
import com.dedagroup.springboottraining.dto.UserDto;
import com.dedagroup.springboottraining.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/user")
//@CrossOrigin("http://localhost:4200")
@Controller
public class UserController {

    private UserService userService;
    private UserMapper userMapper;


    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/autentica")
    public ResponseEntity<String> getMessageAuth() {
        String message = "autenticato correttamente";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    //    @GetMapping("/all")
//    public ResponseEntity<List<UserDto>> getAllUsers (){
//        List<UserDto> allUsers = userService.getAllUsers();
//        return new ResponseEntity<>(allUsers, HttpStatus.OK);
//    }

    //    @PostMapping("/new")
//    public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto userDto) {
//        UserDto newUserDto = userService.createNewUser(userDto);
//        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
//    }
    @PostMapping("/create")
    public String createNewUser(@Valid @ModelAttribute UserDto userDto, Model model) {
        UserDto newUser = userService.createNewUser(userDto);
        model.addAttribute("newUser", newUser);
        return "redirect:/user/all/view";
    }





//VERSIONING - uri mapping
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> showAllUsers(Model model) {
        List<UserDto> allUsers = userService.getAllUsers();
//        model.addAttribute("users", allUsers);
//        return "users";
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
//    @GetMapping("/all/v2")
//    public ResponseEntity<List<UtenteDto>> mostraUtenti() {
//        List<UtenteDto> allUsers = userService.getUtenti();
////        model.addAttribute("users", allUsers);
////        return "users";
//        return new ResponseEntity<>(allUsers, HttpStatus.OK);
//    }


//VERSIONING Request Parameter
//    @GetMapping(value = "/utenti", params = "version=1")
//    public ResponseEntity<List<UserDto>> showAllUsers(@RequestParam("version") String version) {
//        List<UserDto> allUsers = userService.getAllUsers();
//        return new ResponseEntity<>(allUsers, HttpStatus.OK);
//    }

//    @GetMapping(value = "/utenti", params = "version=2")
//    public ResponseEntity<List<UtenteDto>> mostraUtenti(@RequestParam("version") String version) {
//        List<UtenteDto> allUsers = userService.getUtenti();
//        return new ResponseEntity<>(allUsers, HttpStatus.OK);
//    }


//VERSIONING - CUSTOM HEADER
    @GetMapping(value = "/utenti", headers = "API-VERSION=1")
    @ResponseBody
    public MappingJacksonValue showAllUsersNomeCognomeHeader() {
        List<UserDto> allUsers = userService.getAllUsers();
        return wrapUserListNomeCognome(allUsers);
    }

    @GetMapping(value = "/utenti", headers = "API-VERSION=2")
    @ResponseBody
    public MappingJacksonValue showAllUsersAnagraficaHeader() {
        List<UserDto> allUsers = userService.getAllUsers();
        return wrapUserListAnagrafica(allUsers);
    }



////VERSIONING - CUSTOM MEDIA TYPESVERSIONING - CUSTOM MEDIA TYPES
    @GetMapping(value = "/utenti", produces ="application/vnd.test.user.v1+json")
    @ResponseBody
    public MappingJacksonValue showAllUsersNomeCognomeMediaType() {
        List<UserDto> allUsers = userService.getAllUsers();
        return wrapUserListNomeCognome(allUsers);
    }

    @GetMapping(value = "/utenti", produces = "application/vnd.test.user.v2+json")
    @ResponseBody
    public MappingJacksonValue showAllUsersAnagraficaMediaType() {
        List<UserDto> allUsers = userService.getAllUsers();
        return wrapUserListAnagrafica(allUsers);
    }




// FILTER MAPPING JACKSON VALUE
    public MappingJacksonValue wrapUserListNomeCognome(List<UserDto> list){
        //valorizza first, last name
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName", "lastName");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", filter);
        MappingJacksonValue wrapper = new MappingJacksonValue(list);
        wrapper.setFilters(filterProvider);
        return wrapper;
    }

    public MappingJacksonValue wrapUserListAnagrafica(List<UserDto> list){
        //valorizza anagrafica
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("anagraficaDto");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", filter);
        MappingJacksonValue wrapper = new MappingJacksonValue(list);
        wrapper.setFilters(filterProvider);
        return wrapper;
    }












    @GetMapping("/email")
    public ResponseEntity<UserDto> findUserByEmail(@RequestParam String email) {
        UserDto userByEmail = userService.findUserByEmail(email);
        return new ResponseEntity<>(userByEmail, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userDto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        String response = "User deleted successfully";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<UserDto> activateUser(@PathVariable("id") Long id) {
        UserDto activatedUser = userService.activateUser(id);
        return new ResponseEntity<>(activatedUser, HttpStatus.OK);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<UserDto> deactivateUser(@PathVariable("id") Long id) {
        UserDto deactivatedUser = userService.deactivateUser(id);
        return new ResponseEntity<>(deactivatedUser, HttpStatus.OK);
    }


    @PutMapping("/joinGroup/{userId}")
    public ResponseEntity<UserDto> updateUserGroups(
            @PathVariable("userId") Long userId,
            @RequestBody List<Long> idGroups) {
        UserDto joinedUser = userService.updateUserGroups(userId, idGroups);
        return new ResponseEntity<>(joinedUser, HttpStatus.OK);
    }


    //MICROSERVIZI
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        UserDto user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/flagAttivo/{flag}")
    @ResponseBody
    public ResponseEntity<List<Long>> getUsersByFlag(@PathVariable("flag") boolean flag) {
        List<Long> idsByFlag = userService.findIdsByFlagAttivoIs(flag);
        return new ResponseEntity<>(idsByFlag, HttpStatus.OK);
    }



    //AUTENTICAZIONE E THYMELEAF
    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute UserDto userDto, Model model) {
        UserDto user = userService.registerUser(userDto);
        boolean registration = true;
        String registrationEmailMssg = "Ti abbiamo inviato una mail all'indirizzo " + userDto.getEmail() + ". Per completare la registrazione" +
                " clicca sul link che trovi nella mail.";
        model.addAttribute("registrationEmailMssg", registrationEmailMssg);
        model.addAttribute("user", user);
        model.addAttribute("registration", registration);
        model.addAttribute("authToken", userDto.getActivationToken());
        return "home";
    }

    @GetMapping("/register/{authToken}")
    public String getAndCheckAuthToken(@PathVariable("authToken") String authToken, Model model) {
        UserDto userToActivate = userService.getAndCheckAuthToken(authToken);
        model.addAttribute("userToActivate", userToActivate);
        return "password_registration";
    }


    @PostMapping("/register/password/{id}")
    public String updatePasswordAndActivateUser(@PathVariable("id") Long id, @RequestParam("password") String password, Model model) {
       userService.updatePasswordAndActivateUser(password, id);
       boolean registrationSuccess = true;
       String registrationEmailMssg = "la registrazione è stata completata con successo. Ora puoi autenticarti";
       model.addAttribute("registrationEmailMssg", registrationEmailMssg);
       model.addAttribute("registrationSuccess", registrationSuccess);
       return "/login";
    }
}



