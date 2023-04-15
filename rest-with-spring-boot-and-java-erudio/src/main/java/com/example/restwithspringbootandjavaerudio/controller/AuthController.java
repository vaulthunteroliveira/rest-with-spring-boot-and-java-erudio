package com.example.restwithspringbootandjavaerudio.controller;

import com.example.restwithspringbootandjavaerudio.data.vo.v1.security.AccountCredentialsRequest;
import com.example.restwithspringbootandjavaerudio.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authetication Endpoint")
public class AuthController {

    @Autowired
    AuthService authService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates an user and resturns a token.")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsRequest data){
        if (checkData(data)) return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Invalid client request!");

        var token = authService.signin(data);

        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Invalid client request!");

        return token;

    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Refresh token for authenticated user and returns a token.")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken){

        if(checkData(username, refreshToken)) return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Invalid client request!");

        ResponseEntity token = authService.refreshToken(username, refreshToken);

        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    private boolean checkData(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

    private boolean checkData(AccountCredentialsRequest data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
