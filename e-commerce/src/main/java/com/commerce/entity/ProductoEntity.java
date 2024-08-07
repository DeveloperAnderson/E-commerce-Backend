package com.commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productos", schema = "commerce")
@NoArgsConstructor
public class ProductoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(length = 100)
    private String categoria;

    @Column(nullable = false)
    private Boolean activo;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    private InventarioEntity inventario;


}
