package com.commerce.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "usuarios", schema = "commerce" )
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(nullable = false, length = 30)
    private String  username;

    @Column(name = "usua_password", nullable = false )
    private String password;

    @Column(name = "usua_email", nullable = false, length = 100)
    private String email;

    @Column(name = "usua_locked", nullable = false)
    private Boolean locked;

    @Column(name = "usua_disabled", nullable = false)
    private Boolean disabled;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;


}