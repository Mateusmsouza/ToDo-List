package com.example.todolist.model.authorization;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authorization")
public class Authorization implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUT_ID")
    private Long id;

    @Override
    public String getAuthority() {
        return null;
    }
}
