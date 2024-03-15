package com.dedagroup.springboottraining.service.serviceImpl;

import com.dedagroup.springboottraining.converter.GroupMapper;
import com.dedagroup.springboottraining.domain.Group;
import com.dedagroup.springboottraining.domain.Role;
import com.dedagroup.springboottraining.dto.GroupDto;
import com.dedagroup.springboottraining.exception.InvalidParameterException;
import com.dedagroup.springboottraining.repository.GroupRepository;
import com.dedagroup.springboottraining.service.GroupService;
import com.dedagroup.springboottraining.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    private RoleService roleService;

    private GroupMapper groupMapper;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, RoleService roleService, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.roleService = roleService;
        this.groupMapper = groupMapper;
    }

    @Override
    public GroupDto findAllGrops() {
        return null;
    }

    @Override
    public Group findGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new InvalidParameterException("No group found with id: " + id));
    }

    @Override
    public List<Group> findGroupsByIdList(List<Long> idList) {
        return groupRepository.findGroupsByIdIn(idList);
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        Group group = new Group();
        groupMapper.dtoToEntity(groupDto, group);
        groupRepository.save(group);
        return groupMapper.entityToDto(group, groupDto);
    }

    @Override
    public GroupDto updateGroup(GroupDto groupDto, Long id) {
        Group group = findGroupById(id);
        groupMapper.dtoToEntity(groupDto, group);
        groupRepository.save(group);
        return groupMapper.entityToDto(group, groupDto);
    }

    @Override
    public GroupDto linkRolesToGroup(List<Long> roleIds, Long id) {
        Group group = findGroupById(id);
        List<Role> roles = roleService.findRolesByIdIn(roleIds);
        group.getRoles().clear();
        group.getRoles().addAll(roles);
        groupRepository.save(group);
        return groupMapper.entityToDto(group, new GroupDto());
    }


}
