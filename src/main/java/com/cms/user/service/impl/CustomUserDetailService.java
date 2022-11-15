//package com.cms.user.service.impl;
//
//import com.cms.user.entity.CustomUserDetails;
//import com.cms.user.entity.User;
//import com.cms.user.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ObjectUtils;
//
//import java.util.Optional;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByEmail(username);
//        if(ObjectUtils.isEmpty(user)){
//            throw new UsernameNotFoundException("User not found...");
//        }
//        return new CustomUserDetails(user.get());
//    }
//}
