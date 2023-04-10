package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.data.vo.v1.security.AccountCredentialsRequest;
import com.example.restwithspringbootandjavaerudio.data.vo.v1.security.TokenResponse;
import com.example.restwithspringbootandjavaerudio.model.User;
import com.example.restwithspringbootandjavaerudio.repositories.UserRepository;
import com.example.restwithspringbootandjavaerudio.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity signin(AccountCredentialsRequest data){
        try {

            String username = data.getUsername();
            String password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = userRepository.findByUsername(username);

            TokenResponse tokenResponse;

            if (user != null){
                tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles());
            }else {
                throw new UsernameNotFoundException("Username" + username + "not found");
            }

            return ResponseEntity.ok(tokenResponse);
        }catch (Exception e){
            throw  new BadCredentialsException("Invalid username or password!");
        }
    }

}
