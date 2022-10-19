package com.healthofficer;

import com.healthofficer.dao.MemberRepository;
import com.healthofficer.domain.member.Member;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Commit
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class 맴버등록_TEST {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void 맴버등록(){
        for(int i = 0; i < 5; i++){
            Member m = Member.builder().email(i+"email").name("name_"+i).age(i).phone(i+"phone").build();
            //memberRepository.save(m);
        }
    }

    @Test
    void 맴버확인(){
        List<Member> result = memberRepository.findAll();
        List<Member> result2 = memberRepository.findMemberByNameOrAge("name_0",3);
        assertEquals(result.size(), 5);
        assertEquals(result.get(0).getName(), "name_0");
        assertAll(

        );
    }

    //@AfterEach
    //void 맴버삭제(){
    //    memberRepository.deleteAll();
    //}
}
