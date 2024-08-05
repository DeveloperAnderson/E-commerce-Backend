package com.commerce.serviceImpl;

import com.commerce.DTO.RegisterDto;
import com.commerce.DTO.UserEntityDto;
import com.commerce.entity.UserEntity;
import com.commerce.entity.UserRoleEntity;
import com.commerce.repository.IUserRepository;
import com.commerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserImpl  implements UsuarioService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }


    public Optional<UserEntityDto> findUser(String usuario) {
        Optional<UserEntity> userEntity = this.userRepository.findById(usuario);
        if(userEntity.isPresent()){
            //return Optional.of(new UserEntityDto(userEntity.get().getUsername(), userEntity.get().getEmail(), userEntity.get().getLocked(), userEntity.get().getDisabled()));
        }else{
            return Optional.empty();
        }
        return Optional.empty();
    }


    public Optional<List<UserEntityDto>> getUsers() {
        System.out.println("Buscando usuarios...");
        Iterable<UserEntity> userEntities = this.userRepository.findAll();
        if (userEntities != null) {
            System.out.println("Usuarios encontrados ");
        } else {
            System.out.println("No se encontraron usuarios");
            return Optional.empty();
        }

        List<UserEntityDto> userEntityDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserEntityDto userEntityDto = new UserEntityDto(
                    userEntity.getUsername(),
                    userEntity.getEmail(),
                    userEntity.getLocked(),
                    userEntity.getDisabled(),
                    userEntity.getRoles().stream().map(UserRoleEntity::getRole).collect(Collectors.toList())
            );
            userEntityDtos.add(userEntityDto);
        }

        System.out.println("Usuarios encontrados: " + userEntityDtos.size());
        return Optional.of(userEntityDtos);
    }


    public void registerUser(RegisterDto registerDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(registerDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword())); // Encode password
        userEntity.setEmail(registerDto.getEmail());
        userEntity.setLocked(true); // Por defecto
        userEntity.setDisabled(true); // Por defecto
        userRepository.save(userEntity);



        List<UserRoleEntity> roles;
        if (registerDto.getRoles() == null) {
            roles = List.of(createDefaultRole(userEntity));
        } else {
            roles = registerDto.getRoles().stream().map(role -> createRole(userEntity, role)).collect(Collectors.toList());
        }
        userRoleRepository.saveAll(roles);
    }


    private UserRoleEntity createDefaultRole(UserEntity userEntity) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUsername(userEntity.getUsername());
        userRoleEntity.setRole("USER");
        userRoleEntity.setGrantedDate(LocalDateTime.now());
        userRoleEntity.setUser(userEntity);
        return userRoleEntity;
    }


    private UserRoleEntity createRole(UserEntity userEntity, String role) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUsername(userEntity.getUsername());
        userRoleEntity.setRole(role);
        userRoleEntity.setGrantedDate(LocalDateTime.now());
        userRoleEntity.setUser(userEntity);
        return userRoleEntity;
    }
}
