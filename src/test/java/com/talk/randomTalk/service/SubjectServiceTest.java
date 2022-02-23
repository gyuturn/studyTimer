package com.talk.randomTalk.service;

import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.domain.Subject;
import com.talk.randomTalk.repository.MemberRepository;
import com.talk.randomTalk.repository.SubjectRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
class SubjectServiceTest {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    MemberService  memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    SubjectService subjectService;


    @Test
    public void 과목생성() {
        //given
        Member member = Member.createMember("test5674", "1234", "kim", "");
        memberService.join(member);

        //when
        Long subjectId = subjectService.addSubject(member.getMemberId(), "testSubject");
        Subject subject = subjectRepository.findOne(subjectId);

        //then
        Assertions.assertThat(subject.getMember()).isEqualTo(member);
    }
}