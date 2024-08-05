package com.commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventario", schema = "commerce")
@NoArgsConstructor
public class InventarioEntity {
    @Id
    @Column(name = "producto_id")
    private Long productoId;

    @Column(nullable = false)
    private Integer cantidad;

    @OneToOne
    @MapsId
    @JoinColumn(name = "producto_id")
    private com.commerce.entity.ProductoEntity producto;
}
