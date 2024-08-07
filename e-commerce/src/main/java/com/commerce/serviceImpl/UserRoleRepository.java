package com.commerce.serviceImpl;

import com.commerce.entity.UserRoleEntity;
import com.commerce.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// In `UserRoleRepository` interface
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleId> {
    List<UserRoleEntity> findByUsername(String username);

}