package com.pbukki.creswave.repository;

import com.pbukki.creswave.entity.Role;
import com.pbukki.creswave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String roleUser);
}
