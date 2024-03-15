package com.dedagroup.springboottraining.service;

import com.dedagroup.springboottraining.domain.Role;
import com.dedagroup.springboottraining.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAllRoles();

    RoleDto createRole(RoleDto roleDto);

    RoleDto updateRole(RoleDto roleDto, Long id);

//    Role findRoleById(Long id);

    List<Role> findRolesByIdIn(List<Long> rolesIds);


}
