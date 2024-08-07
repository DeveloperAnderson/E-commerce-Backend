package com.commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ventas", schema = "commerce")
@NoArgsConstructor
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductoEntity producto;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "username_id", nullable = false)
    private UserEntity usuario;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private Date fechaVenta;

}
