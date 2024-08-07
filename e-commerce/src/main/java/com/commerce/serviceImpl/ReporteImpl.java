package com.commerce.serviceImpl;

import com.commerce.projections.ProjectionProduct;
import com.commerce.projections.ProjectionUser;
import com.commerce.repository.IProductRepository;
import com.commerce.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteImpl implements ReporteService {


    @Autowired
    private IProductRepository productoRepository;

    @Override
    public List<ProjectionProduct> obtenerProductosActivos() {
        List<ProjectionProduct> productosActivos = productoRepository.findByActivoTrue();
        if (productosActivos.isEmpty()) {
            return null;
        }
        return productosActivos;
    }

    @Override
    public List<ProjectionProduct> obtenerTop5ProductosVendidos() {
        List<ProjectionProduct> top5ProductosVendidos = productoRepository.findTop5ProductosVendidos();
        if (top5ProductosVendidos.isEmpty()) {
            return null;
        }

        return top5ProductosVendidos;
    }

    @Override
    public List<ProjectionUser> obtenerTop5ClientesFrecuentes() {
        List<ProjectionUser> top5ClientesFrecuentes = productoRepository.findTop5ClientesFrecuentes();
        if (top5ClientesFrecuentes.isEmpty()) {
            return null;
        }
        return top5ClientesFrecuentes;


    }
}
