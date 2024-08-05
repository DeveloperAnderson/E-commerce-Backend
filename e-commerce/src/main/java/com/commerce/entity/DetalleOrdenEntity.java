package com.commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detalles_orden", schema = "commerce")
@NoArgsConstructor
public class DetalleOrdenEntity {

    @EmbeddedId
    private DetalleOrdenId id;

    @ManyToOne
    @MapsId("ordenId")
    @JoinColumn(name = "orden_id")
    private OrdenEntity orden;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precio;
}
