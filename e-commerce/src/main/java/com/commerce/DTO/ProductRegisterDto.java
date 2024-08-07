package com.commerce.DTO;


import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class ProductRegisterDto implements Serializable {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private Long cantidad;
    private Boolean activo;

}
