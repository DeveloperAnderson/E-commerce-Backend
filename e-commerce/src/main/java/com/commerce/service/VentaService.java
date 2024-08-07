package com.commerce.service;

import com.commerce.entity.VentaEntity;
import org.springframework.stereotype.Service;

@Service
public interface VentaService {
    VentaEntity registrarVenta(VentaEntity venta);
}
