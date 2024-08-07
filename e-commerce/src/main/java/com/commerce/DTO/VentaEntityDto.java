package com.commerce.DTO;

import com.commerce.entity.VentaEntity;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link VentaEntity}
 */
@Value
public class VentaEntityDto implements Serializable {
    Long idProducto;
    Double precioProducto;
    String username;
    int cantidad;
}