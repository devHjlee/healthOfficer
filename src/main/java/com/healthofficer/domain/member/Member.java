package com.healthofficer.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="email")
    private Collection<Exercise> exercises;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    private int age;

    private String phone;

    @Builder
    public Member(String email,String name,int age, String phone){
        Assert.notNull(phone, "phone must not be null");

        this.email = email;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.exercises = getExercises();
    }
}