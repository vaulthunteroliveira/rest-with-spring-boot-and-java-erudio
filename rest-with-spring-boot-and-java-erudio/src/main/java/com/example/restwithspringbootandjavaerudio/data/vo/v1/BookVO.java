package com.example.restwithspringbootandjavaerudio.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
//@ToString
@EqualsAndHashCode(of = "key")
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "author", "lauchDate", "price", "title"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2139537926265903959L;


    @JsonProperty("id")
    @Mapping("id")
    private Long key;

    private String author;

    private Date lauchDate;

    private BigDecimal price;

    private String title;
}
