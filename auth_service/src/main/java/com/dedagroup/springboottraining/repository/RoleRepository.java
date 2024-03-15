package com.dedagroup.springboottraining.repository;

import com.dedagroup.springboottraining.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {


    List<Role> findRolesByIdIn(List<Long> rolesIds);

}
