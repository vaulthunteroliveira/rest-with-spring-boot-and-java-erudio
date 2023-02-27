package com.example.restwithspringbootandjavaerudio.data.vo.v1;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PersonVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2139537926265903959L;

    private Long id;
    private String firstname;
    private String lastname;
    private String address;
    private String gender;
    private boolean addicted;
}
