package com.exadel.spring_security_jwt_access_refresh.repo;

import com.exadel.spring_security_jwt_access_refresh.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
