package com.uca.pncparcialfinalhotel.repository;

import com.uca.pncparcialfinalhotel.entity.Role;
import com.uca.pncparcialfinalhotel.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName name);
}
