package com.dedagroup.springboottraining.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "authorization")
public class Authorization extends BaseEntity{

    public Authorization() {
    }

    public Authorization(String codeAuth, String descriptionAuth) {
        super();
        this.codeAuth = codeAuth;
        this.descriptionAuth = descriptionAuth;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code_auth")
    private String codeAuth;

    @Column(name = "description_auth")
    private String descriptionAuth;


//    @ManyToMany
//    private List<Role> roles;
}
