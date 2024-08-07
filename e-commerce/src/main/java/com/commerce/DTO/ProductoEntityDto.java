package com.commerce.DTO;

import com.commerce.entity.ProductoEntity;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ProductoEntity}
 */
@Value
public class ProductoEntityDto implements Serializable {
    String nombre;
    String descripcion;
    Double precio;
    Boolean activo;
}