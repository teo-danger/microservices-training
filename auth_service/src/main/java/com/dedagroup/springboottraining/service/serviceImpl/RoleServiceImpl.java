package com.dedagroup.springboottraining.service.serviceImpl;

import com.dedagroup.springboottraining.converter.RoleMapper;
import com.dedagroup.springboottraining.domain.Role;
import com.dedagroup.springboottraining.dto.RoleDto;
import com.dedagroup.springboottraining.exception.InvalidParameterException;
import com.dedagroup.springboottraining.repository.RoleRepository;
import com.dedagroup.springboottraining.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    private RoleMapper roleMapper;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }


    @Override
    public List<RoleDto> findAllRoles() {
        return null;
    }



    public Role findRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(()-> new InvalidParameterException("No role found with id: " + id));
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = new Role();
        roleMapper.dtoToEntity(roleDto, role);
        roleRepository.save(role);
        return roleMapper.entityToDto(role, roleDto);
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Long id) {
        Role role = findRoleById(id);
        roleMapper.dtoToEntity(roleDto, role);
        roleRepository.save(role);
        return roleMapper.entityToDto(role, roleDto);
    }

    @Override
    public List<Role> findRolesByIdIn(List<Long> rolesIds) {
        return roleRepository.findRolesByIdIn(rolesIds);

    }
}
