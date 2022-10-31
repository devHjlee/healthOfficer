package com.healthofficer.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    private int age;

    private String phone;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="email")
    private Collection<Exercise> exercises;

    @Builder
    public Member(String email,String name,int age, String phone){
        this.email = email;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
}