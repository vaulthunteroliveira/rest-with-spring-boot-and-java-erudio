package com.example.restwithspringbootandjavaerudio.controller;

import com.example.restwithspringbootandjavaerudio.data.vo.v1.security.AccountCredentialsRequest;
import com.example.restwithspringbootandjavaerudio.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private boolean checkData(AccountCredentialsRequest data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
