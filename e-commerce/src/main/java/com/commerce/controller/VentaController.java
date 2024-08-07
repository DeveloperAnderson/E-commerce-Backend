package com.commerce.controller;

import com.commerce.DTO.ProductoEntityDto;
import com.commerce.DTO.VentaEntityDto;
import com.commerce.entity.VentaEntity;
import com.commerce.repository.VentaServiceImpl;
import com.commerce.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.commerce.config.contantes.ApiVersion.CON_API;

@RestController
@RequestMapping(CON_API+"/ventas")
public class VentaController {

    @Autowired
    private VentaServiceImpl ventaService;

    @PostMapping("/registrar")
    public ResponseEntity<VentaEntityDto> registrarVenta(@RequestBody VentaEntityDto ventaDto) {
        if (ventaDto != null){
            VentaEntityDto ventaRegistrada = ventaService.registrarVenta(ventaDto);
            return ResponseEntity.ok(ventaRegistrada);
        }else{
            return ResponseEntity.badRequest().build();
        }


    }
}
