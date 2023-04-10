package com.example.restwithspringbootandjavaerudio.data.vo.v1.security;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -8478689928618742712L;

    private String username;
    private boolean authenticated;
    private Date create;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

}
