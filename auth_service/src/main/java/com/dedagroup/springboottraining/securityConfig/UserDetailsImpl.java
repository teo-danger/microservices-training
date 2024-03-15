//package com.dedagroup.springboottraining.securityConfig;
//
//import com.dedagroup.springboottraining.domain.Group;
//import com.dedagroup.springboottraining.domain.Role;
//import com.dedagroup.springboottraining.domain.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.*;
//
//
//public class UserDetailsImpl implements UserDetails {
//
//
//    private User user;
//
//    public UserDetailsImpl(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        List<Set<Role>> listsOfRoleLists = user.getGroups()
//                .stream()
//                .map(Group::getRoles)
//                .toList();
//        List<Role> roles = listsOfRoleLists
//                .stream()
//                .flatMap(Set::stream)
//                .toList();
//        for(Role r : roles){
//            authorities.add(new SimpleGrantedAuthority(r.getName()));
//        }
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return user.isFlagAttivo();
//    }
//}
