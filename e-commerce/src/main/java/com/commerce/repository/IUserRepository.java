package com.commerce.repository;

import com.commerce.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, String > {
    Optional<UserEntity> findByUsername(String email);

}
