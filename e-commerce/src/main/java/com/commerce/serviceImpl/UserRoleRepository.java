package com.commerce.serviceImpl;

import com.commerce.entity.UserRoleEntity;
import com.commerce.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

// In `UserRoleRepository` interface
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleId> {
}