package com.dedagroup.springboottraining.controller;

import com.dedagroup.springboottraining.dto.GroupDto;
import com.dedagroup.springboottraining.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/new")
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto){
        GroupDto newGroup = groupService.createGroup(groupDto);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto, @PathVariable("id") Long id){
        GroupDto updatedGroup = groupService.updateGroup(groupDto, id);
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }


    @PutMapping("/link-to-roles/{id}")
    public ResponseEntity<GroupDto> linkGroupToRoles(@RequestBody List<Long> roleIds, @PathVariable("id") Long id){
        GroupDto linkedGroup = groupService.linkRolesToGroup(roleIds, id);
        return new ResponseEntity<>(linkedGroup, HttpStatus.OK);
    }

}
