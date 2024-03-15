package com.dedagroup.springboottraining.domain;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    @Column
    private String name;


//    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "group_role",
//            joinColumns = @JoinColumn(name = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id"))
//    private List<Group> groups = new ArrayList<>();


}
