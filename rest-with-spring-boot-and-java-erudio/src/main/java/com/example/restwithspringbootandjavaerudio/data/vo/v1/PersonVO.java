package com.example.restwithspringbootandjavaerudio.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of = "key")
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "firstname", "lastname", "gender", "address", "arrombado"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2139537926265903959L;


    @JsonProperty("id")
    @Mapping("id")
    private Long key;
    private String firstname;
    private String lastname;
    private String address;
    private String gender;
//    @JsonProperty("asshole")
    private boolean arrombado;
}
