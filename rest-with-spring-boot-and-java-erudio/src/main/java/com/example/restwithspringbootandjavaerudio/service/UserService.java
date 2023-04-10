package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.model.User;
import com.example.restwithspringbootandjavaerudio.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {

    private final Logger log = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public User findByUsername(String username) {
//        log.info(String.format("finding a user with username %s...", username));
//        User user = userRepository.findByUsername(username);
//        if(user != null){
//            log.info(String.format("user %s was found!", username));
//            return user;
//        }
//        throw new UsernameNotFoundException(String.format("user %s wasn't found!", username));
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(String.format("finding a user by username %s...", username));
        var user = userRepository.findByUsername(username);
        if (user != null){
            log.info(String.format("User %s was found!", user.getUsername()));
            return user;
        }
        throw new UsernameNotFoundException(String.format("user %s wasn't found!", username));
    }
}
