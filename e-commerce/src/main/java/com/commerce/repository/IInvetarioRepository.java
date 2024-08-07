package com.commerce.repository;

import com.commerce.entity.InventarioEntity;
import com.commerce.entity.ProductoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvetarioRepository extends CrudRepository<InventarioEntity, Long> {
    //InventarioEntity findById(ProductoEntity userEntity);
}
