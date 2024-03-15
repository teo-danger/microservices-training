package com.dedagroup.springboottraining.service;

import com.dedagroup.springboottraining.domain.Group;
import com.dedagroup.springboottraining.domain.Role;
import com.dedagroup.springboottraining.dto.GroupDto;
import com.dedagroup.springboottraining.dto.RoleDto;

import java.util.List;

public interface GroupService {

    GroupDto findAllGrops();

    Group findGroupById(Long id);

    List<Group> findGroupsByIdList(List<Long> idList);

    GroupDto createGroup(GroupDto groupDto);

    GroupDto updateGroup(GroupDto groupDto, Long id);

    GroupDto linkRolesToGroup(List<Long> roleIds, Long id);

}
