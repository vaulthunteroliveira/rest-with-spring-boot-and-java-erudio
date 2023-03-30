package com.example.restwithspringbootandjavaerudio.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 7409905689592555087L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author")
    private String author;

    @Temporal(TemporalType.DATE)
    @Column(name = "launch_date", nullable = false)
    private Date lauchDate;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "title")
    private String title;
}
