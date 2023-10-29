package com.simran.demo.repository;

import com.simran.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
}
