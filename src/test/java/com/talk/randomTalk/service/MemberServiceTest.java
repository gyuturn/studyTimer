package com.talk.randomTalk.service;

import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = Member.createMember("1234", "1234", "김규민", "kksy2423@navert.com");
        //when
        Long saveId = memberService.join(member);
        //then
        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(saveId));
    }

    @Test
    public void 중복회원검증() throws Exception {
        //given
        Member memberA = Member.createMember("1234", "1234", "asd", "asds");
        Member memberB = Member.createMember("1234", "1234", "asd", "asds");

        //when
        memberService.join(memberA);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> memberService.join(memberB));


        //then

    }
}