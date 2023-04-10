package com.example.restwithspringbootandjavaerudio.data.vo.v1.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class AccountCredentialsRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6026005576582959533L;

    private String username;
    private String password;
}
