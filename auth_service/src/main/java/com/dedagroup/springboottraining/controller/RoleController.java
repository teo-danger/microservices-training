package com.dedagroup.springboottraining.controller;

import com.dedagroup.springboottraining.dto.GroupDto;
import com.dedagroup.springboottraining.dto.RoleDto;
import com.dedagroup.springboottraining.service.GroupService;
import com.dedagroup.springboottraining.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/new")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto){
        RoleDto newRole = roleService.createRole(roleDto);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto roleDto, @PathVariable("id") Long id){
        RoleDto updatedRole = roleService.updateRole(roleDto, id);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }
}
