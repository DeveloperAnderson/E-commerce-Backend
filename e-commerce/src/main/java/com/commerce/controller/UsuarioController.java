package com.commerce.controller;

import com.commerce.DTO.RegisterDto;
import com.commerce.DTO.UpdateDto;
import com.commerce.DTO.UserEntityDto;
import com.commerce.serviceImpl.UserImpl;
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
@RequestMapping(CON_API+"/usuario")
//@Tag(name = "Usuario", description = "API de Usuario")
public class UsuarioController {

    private final UserImpl userImpl;

    @Autowired
    public UsuarioController(UserImpl userImpl) {
        this.userImpl = userImpl;
    }


    @GetMapping("/findUser/{username}")
    public Optional<UserEntityDto> findUser(@PathVariable String username){
        System.out.println("Buscando usuario...");
        return this.userImpl.findUser(username);
    }

    @GetMapping("/getUsers")
    public Optional<List<UserEntityDto>> getUsers(){
        return this.userImpl.getUsers();
    }



    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){
        this.userImpl.registerUser(registerDto);
        // Devolver una respuesta JSON con el estado HTTP 201 (Created)
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario registrado exitosamente");
        response.put("username", registerDto.getUsername());
        response.put("status", "201");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UpdateDto updateDto){
        this.userImpl.updateUser(updateDto);
        // Devolver una respuesta JSON con el estado HTTP 201 (Created)
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario actualizado exitosamente");
        response.put("username", updateDto.getUsername());
        response.put("status", "201");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody RegisterDto registerDto){
        System.out.println("Creando usuario... Controller");
        this.userImpl.createUser(registerDto);
        // Devolver una respuesta JSON con el estado HTTP 201 (Created)
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario creado exitosamente");
        response.put("username", registerDto.getUsername());
        response.put("status", "201");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        System.out.println("Eliminando usuario... "+username);
        this.userImpl.deleteUser(username);
        // Devolver una respuesta JSON con el estado HTTP 201 (Created)
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario eliminado exitosamente");
        response.put("username", username);
        response.put("status", "201");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
