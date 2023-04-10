package com.example.restwithspringbootandjavaerudio.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority, Serializable {

    @Serial
    private static final long serialVersionUID = -8606464315420663585L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private PermissionEnum description;

    @Override
    public String getAuthority() {
        return this.description.toString();
    }


    enum PermissionEnum {
        ADMIN, MANAGER, COMMON_USER;
    }
}
