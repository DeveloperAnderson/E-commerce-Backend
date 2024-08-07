package com.commerce.DTO;

import com.commerce.entity.InventarioEntity;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link InventarioEntity}
 */
@Value
public class InventarioEntityDto implements Serializable {
    Long productoId;
    Integer cantidad;
}