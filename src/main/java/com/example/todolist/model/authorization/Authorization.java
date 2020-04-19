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

    @Column(name = "AUT_NAME")
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthorizationName(String authority) {
        this.authority = authority;
    }
}
