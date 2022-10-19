package com.healthofficer.dao;

import com.healthofficer.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findMemberByNameOrAge(String name,int age);
}
