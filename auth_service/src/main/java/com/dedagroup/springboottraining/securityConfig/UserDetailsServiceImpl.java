//package com.dedagroup.springboottraining.securityConfig;
//
//import com.dedagroup.springboottraining.domain.User;
//import com.dedagroup.springboottraining.exception.InvalidParameterException;
//import com.dedagroup.springboottraining.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    UserRepository userRepository;
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findUserByUsername(username)
//                .orElseThrow(() -> new InvalidParameterException("No user found with username: " + username));
//        return new UserDetailsImpl(user);
//    }
//}
