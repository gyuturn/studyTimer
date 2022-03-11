package com.talk.randomTalk.service;

import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.domain.Subject;
import com.talk.randomTalk.form.LoginForm;
import com.talk.randomTalk.repository.MemberRepository;
import com.talk.randomTalk.repository.SubjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalTime;
import java.util.ArrayList;
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
    @Autowired
    SubjectService subjectService;
    @Autowired
    SubjectRepository subjectRepository;

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

//    @Test
//    public void 총시간계산() throws Exception{
//        //given
//        Member member = Member.createMember("test423231", "1234", "test423121", "");
//        Long memberId = memberService.join(member);
//
//        Long subject1 = subjectService.addSubject(memberId, "subject1123");
//        Long subject2 = subjectService.addSubject(memberId, "subject22");
//
//        Subject one = subjectRepository.findOne(subject1);
//        Subject two = subjectRepository.findOne(subject2);
//
//        one.setTime(LocalTime.of(0, 0, 40));
//        two.setTime(LocalTime.of(0, 0, 30));
//
//        //when
//        memberService.calcTotalTime(member);
//
//        //then
//        Assertions.assertThat(member.getTotalTime()).isEqualTo(LocalTime.of(0, 1, 10));
//    }
}