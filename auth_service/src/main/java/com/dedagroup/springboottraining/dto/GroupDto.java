package com.dedagroup.springboottraining.dto;


import com.dedagroup.springboottraining.domain.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private Long id;
    private String code;
    private String description;
    private Set<Role> roles;
}
