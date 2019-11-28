package com.santatostada.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users_db")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String status;

    @Column
    private String email;

    public User(String name, String phoneNumber, String status, String email){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.email = email;
    }

    public User(){ }
}
