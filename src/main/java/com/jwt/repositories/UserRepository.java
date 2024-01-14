package com.jwt.repositories;

import com.jwt.entities.RoleEntity;
import com.jwt.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findFirstByEmail(String email);
    UserEntity findByRole(RoleEntity role);
}
