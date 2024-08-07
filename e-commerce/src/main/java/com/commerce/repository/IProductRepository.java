package com.commerce.repository;

import com.commerce.projections.ProjectionProduct;
import com.commerce.entity.ProductoEntity;
import com.commerce.projections.ProjectionUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends CrudRepository<ProductoEntity, Long> {

    Optional<ProductoEntity> findByNombre(String nombre);

    // i. Productos activos
    @Query("SELECT p.nombre as nombre, p.activo as activo, p.precio as precio from ProductoEntity p where p.activo ")
    List<ProjectionProduct> findByActivoTrue();

    // ii. Top 5 de lo más vendido
    @Query("SELECT v.producto.nombre as nombre , v.producto.activo as activo, v.producto.precio as precio  FROM VentaEntity v  " +
            "ORDER BY v.producto.nombre   ASC LIMIT 5")
    List<ProjectionProduct> findTop5ProductosVendidos();

    // iii. Top 5 de los clientes frecuentes
    @Query("SELECT u.username as nombre, v.producto.nombre as productNombre, v.cantidad as cantidad FROM VentaEntity v JOIN UserEntity u ON v.usuario.id = u.id " +
            "ORDER BY u.username  ASC LIMIT 5")
    List<ProjectionUser> findTop5ClientesFrecuentes();




}
