package com.commerce.repository;

import com.commerce.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserEntity, String > {


}
