package com.dedagroup.springboottraining.securityConfig;

import com.dedagroup.springboottraining.dto.UserDto;
import com.dedagroup.springboottraining.exception.InvalidParameterException;
import com.dedagroup.springboottraining.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Collection;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private UserService userService;


    public CustomAuthProvider(@Lazy PasswordEncoder passwordEncoder, @Lazy UserService userService){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDto user = userService.loadUserByUsername(username);
        if (user == null) {
            throw new InvalidParameterException("User not found");
        }
        if (!passwordEncoder.matches(password,user.getPassword())){
                throw new InvalidParameterException("Invalid credentials") {};
        }

        SecurityContext securityContext = SecurityContextHolder
                .getContext();
        securityContext.setAuthentication(authentication);

        Collection<? extends GrantedAuthority> authorities = userService.getAuthorities(user);
        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                user, null, authorities);
        return authenticated;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
