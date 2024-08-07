package com.commerce.serviceImpl;

import com.commerce.DTO.ProductDto;
import com.commerce.DTO.ProductRegisterDto;
import com.commerce.DTO.RegisterDto;
import com.commerce.DTO.UpdateDto;
import com.commerce.entity.InventarioEntity;
import com.commerce.entity.ProductoEntity;
import com.commerce.entity.UserEntity;
import com.commerce.entity.UserRoleEntity;
import com.commerce.repository.IInvetarioRepository;
import com.commerce.repository.IProductRepository;
import com.commerce.service.ProductService;
import org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements ProductService {

    private final IProductRepository productRepository;
    private final IInvetarioRepository inventarioRepository;
    private final SystemMetricsAutoConfiguration systemMetricsAutoConfiguration;

    public ProductImpl(IProductRepository productRepository, IInvetarioRepository inventarioRepository, SystemMetricsAutoConfiguration systemMetricsAutoConfiguration) {
        this.productRepository = productRepository;
        this.inventarioRepository = inventarioRepository;
        this.systemMetricsAutoConfiguration = systemMetricsAutoConfiguration;
    }

    public Optional<List<ProductDto>> getProducts() {
        Iterable<ProductoEntity> productEntities = this.productRepository.findAll();
        if (productEntities != null) {
            System.out.println("Usuarios encontrados ");
        } else {
            System.out.println("No se encontraron usuarios");
            return Optional.empty();
        }

        List<ProductDto> productEntityDtos = new ArrayList<>();
        for (ProductoEntity productEntity : productEntities) {
            ProductDto userEntityDto = new ProductDto(
                    productEntity.getId(),
                    productEntity.getNombre(),
                    productEntity.getDescripcion(),
                    productEntity.getPrecio(),
                    productEntity.getCategoria(),
                    productEntity.getInventario().getCantidad(),
                    productEntity.getActivo()


            );
            productEntityDtos.add(userEntityDto);
        }

        System.out.println("Usuarios encontrados: " + productEntityDtos.size());
        return Optional.of(productEntityDtos);
    }


    public void registerProduct(ProductRegisterDto productRegisterDto) {
        System.out.println("Registrando producto...");
        System.out.println("Cantidad: "+productRegisterDto.getCantidad());
        System.out.println("Nombre: "+productRegisterDto.getNombre());
        System.out.println("Descripcion: "+productRegisterDto.getDescripcion());
        System.out.println("Precio: "+productRegisterDto.getPrecio());
        System.out.println("Categoria: "+productRegisterDto.getCategoria());
        System.out.println("Activo: "+productRegisterDto.getActivo());

        ProductoEntity productEntity = new ProductoEntity();
        productEntity.setNombre(productRegisterDto.getNombre());
        productEntity.setDescripcion(productRegisterDto.getDescripcion());
        productEntity.setPrecio(productRegisterDto.getPrecio());
        productEntity.setCategoria(productRegisterDto.getCategoria());
        productEntity.setActivo(productRegisterDto.getActivo());
        productRepository.save(productEntity);

        System.out.println("Producto registrado con éxito");
        // Registrando cantidad en la tabla inventario
        InventarioEntity inventarioEntity = new InventarioEntity();
        inventarioEntity.setCantidad(Math.toIntExact(productRegisterDto.getCantidad()));
        inventarioEntity.setProducto(productEntity);
        inventarioRepository.save(inventarioEntity);
    }


    public void updateProduct(ProductRegisterDto updateDto) {
        System.out.println("Actualizando usuario...");
        Optional<ProductoEntity> optionalUserEntity = productRepository.findById(updateDto.getId());

        if (optionalUserEntity.isPresent()) {
            System.out.println("Usuario encontrado");
            ProductoEntity userEntity = optionalUserEntity.get();
            userEntity.setNombre(updateDto.getNombre());
            userEntity.setDescripcion(updateDto.getDescripcion());
            userEntity.setPrecio(updateDto.getPrecio());
            userEntity.setCategoria(updateDto.getCategoria());
            userEntity.setActivo(updateDto.getActivo());
            productRepository.save(userEntity);

            // Actualizando cantidad en la tabla inventario
            System.out.println("Actualizando cantidad en la tabla inventario ID del producto: "+userEntity.getId());
            Optional<InventarioEntity> inventarioEntity = inventarioRepository.findById(userEntity.getId());
            if (inventarioEntity.isPresent()) {
                System.out.println("inventarioEntity: "+inventarioEntity);
                System.out.println("Cantidad: "+updateDto.getCantidad());

                inventarioEntity.get().setCantidad(Math.toIntExact(updateDto.getCantidad()));

            } else {
                throw new RuntimeException("User not found");
            }

        } else {
            throw new RuntimeException("User not found");
        }
    }




}
