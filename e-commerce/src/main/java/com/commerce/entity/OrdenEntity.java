package com.commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ordenes", schema = "commerce")
@NoArgsConstructor
public class OrdenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_username", referencedColumnName = "username")
    private UserEntity usuario;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private Double total;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<DetalleOrdenEntity> detalles;
}
