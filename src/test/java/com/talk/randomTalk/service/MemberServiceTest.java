package com.talk.randomTalk.service;

import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.form.LoginForm;
import com.talk.randomTalk.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

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
        Member member = Member.createMember("test1123", "1234", "test1123", "kksy2423@navert.com");
        //when
        Long saveId = memberService.join(member);
        //then
        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(saveId));
    }

    @Test
    public void 중복회원검증() throws Exception {
        //given
        Member memberA = Member.createMember("test2352", "1234", "test2352", "asds");
        Member memberB = Member.createMember("test2352", "1234", "test2352", "asds");

        //when
        memberService.join(memberA);



        //then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> memberService.join(memberB));
    }

    @Test
    public void 로그인() throws Exception {
        //given
        Member member = Member.createMember("test4231", "1234", "test4231", "");
        memberService.join(member);

        LoginForm loginForm = new LoginForm();
        loginForm.setId("test4231");
        loginForm.setPassword("1234");
        //when
        boolean b = memberService.validLogin(loginForm);
        //then
        Assertions.assertThat(b).isEqualTo(true);
    }
}