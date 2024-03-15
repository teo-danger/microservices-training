package com.dedagroup.springboottraining.converter;

import com.dedagroup.springboottraining.domain.Group;
import com.dedagroup.springboottraining.dto.GroupDto;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public Group dtoToEntity (GroupDto groupDto, Group group){
        group.setId(groupDto.getId());
        group.setCode(groupDto.getCode());
        group.setDescription(groupDto.getDescription());
        group.setRoles(groupDto.getRoles());
        return group;
    }

    public GroupDto entityToDto (Group group, GroupDto groupDto){
        groupDto.setId(group.getId());
        groupDto.setCode(group.getCode());
        groupDto.setDescription(group.getDescription());
        groupDto.setRoles(group.getRoles());
        return groupDto;
    }


}
