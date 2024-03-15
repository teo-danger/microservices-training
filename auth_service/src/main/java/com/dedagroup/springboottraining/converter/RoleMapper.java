package com.dedagroup.springboottraining.converter;

import com.dedagroup.springboottraining.domain.Group;
import com.dedagroup.springboottraining.domain.Role;
import com.dedagroup.springboottraining.dto.GroupDto;
import com.dedagroup.springboottraining.dto.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role dtoToEntity(RoleDto roleDto, Role role) {
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }

    public RoleDto entityToDto(Role role, RoleDto roleDto) {
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}
