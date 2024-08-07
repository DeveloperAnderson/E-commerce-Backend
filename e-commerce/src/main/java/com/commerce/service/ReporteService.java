package com.commerce.service;

import com.commerce.projections.ProjectionProduct;
import com.commerce.projections.ProjectionUser;

import java.util.List;

public interface ReporteService {

    List<ProjectionProduct> obtenerProductosActivos();

    List<ProjectionProduct> obtenerTop5ProductosVendidos();

    List<ProjectionUser> obtenerTop5ClientesFrecuentes();
}