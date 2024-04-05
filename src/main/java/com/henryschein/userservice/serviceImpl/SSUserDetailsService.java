/*
package com.henryshain.userservice.serviceImpl;

import com.henryshain.userservice.model.User;
import com.henryshain.userservice.repository.UserRepository;
import com.henryshain.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.henryshain.userservice.serviceImpl.UserServiceImpl;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
public class SSUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SSUserDetailsService.class);
    private UserRepository userRepository;

    public SSUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            //User user = userRepository.findByName(username.toLowerCase());
            User user = userRepository.findByEmail(username.toLowerCase());

            System.out.println("userfound " + user.getName());

            //if (user == null || !user.isEnabled() || user.isBanned()) {
            if (user == null) {
                LOGGER.debug("User not found");
                return null;
            }
            LOGGER.debug("User found");
            Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

            String[] authStrings = user.getRole().split(",");
            for (String authString : authStrings) {
                authorities.add(new SimpleGrantedAuthority(authString));
            }

            // authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                    authorities);

        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }



}
*/




