package com.commerce.controller;

import com.commerce.projections.ProjectionProduct;
import com.commerce.projections.ProjectionUser;
import com.commerce.serviceImpl.ReporteImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.commerce.config.contantes.ApiVersion.CON_API;

@RestController
@RequestMapping(CON_API+"/reportes")
public class ReporteController {

    @Autowired
    private ReporteImpl reporteService;

    @GetMapping("/productos-activos")
    public ResponseEntity<List<ProjectionProduct>> obtenerProductosActivos() {
        List<ProjectionProduct> productosActivos = reporteService.obtenerProductosActivos();
        return ResponseEntity.ok(productosActivos);
    }

    @GetMapping("/top5-productos-vendidos")
    public ResponseEntity<List<ProjectionProduct>> obtenerTop5ProductosVendidos() {
        List<ProjectionProduct> top5ProductosVendidos = reporteService.obtenerTop5ProductosVendidos();
        return ResponseEntity.ok(top5ProductosVendidos);
    }

    @GetMapping("/top5-clientes-frecuentes")
    public ResponseEntity<List<ProjectionUser>> obtenerTop5ClientesFrecuentes() {
        List<ProjectionUser> top5ClientesFrecuentes = reporteService.obtenerTop5ClientesFrecuentes();
        return ResponseEntity.ok(top5ClientesFrecuentes);
    }

}
