package com.commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_role", schema = "commerce")
@IdClass(UserRoleId.class)
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity {

    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private LocalDateTime grantedDate;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private UserEntity user;

}
