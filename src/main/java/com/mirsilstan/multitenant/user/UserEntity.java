package com.mirsilstan.multitenant.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@Data
public class UserEntity {
    @Id
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String state;
}