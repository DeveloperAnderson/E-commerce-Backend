package com.commerce.DTO;


import lombok.Value;

import java.io.Serializable;

@Value
public class ProductDto implements Serializable {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private Integer cantidad;
    private boolean disponible;
}
