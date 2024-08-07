package com.commerce.controller;

import com.commerce.DTO.*;
import com.commerce.serviceImpl.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.commerce.config.contantes.ApiVersion.CON_API;

@RestController
@RequestMapping(CON_API+"/product")
public class ProductController {

    private final ProductImpl productImpl;

    @Autowired
    public ProductController(ProductImpl productImpl) {
        this.productImpl = productImpl;
    }


    @GetMapping("/getProducts")
    public Optional<List<ProductDto>> getProducts(){
        return this.productImpl.getProducts();
    }

    @PostMapping("/registerProduct")
    public ResponseEntity<?> registerUser(@RequestBody ProductRegisterDto productRegisterDto){
        this.productImpl.registerProduct(productRegisterDto);
        // Devolver una respuesta JSON con el estado HTTP 201 (Created)
        Map<String, String> response = new HashMap<>();
        response.put("message", "Producto registrado exitosamente");
        response.put("nombre", productRegisterDto.getNombre());
        response.put("status", "201");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRegisterDto updateDto){
        this.productImpl.updateProduct(updateDto);
        // Devolver una respuesta JSON con el estado HTTP 201 (Created)
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario actualizado exitosamente");
        response.put("username", updateDto.getNombre());
        response.put("status", "201");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
