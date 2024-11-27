package com.example.clothing_sell_website.repository;

import com.example.clothing_sell_website.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRoleId(String roleId);
}
