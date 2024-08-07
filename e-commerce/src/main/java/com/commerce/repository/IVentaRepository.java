package com.commerce.repository;

import com.commerce.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<VentaEntity, Long> {
}
