package com.talk.randomTalk.service;

import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.domain.Subject;
import com.talk.randomTalk.repository.MemberRepository;
import com.talk.randomTalk.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public Long addSubject(Long memberId,String subjectName) {
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);

        //과목클래스 생성
        Subject subject = Subject.createSubject(subjectName, member);

        //과목 저장
        subjectRepository.save(subject);

        return subject.getId();

    }

    public List<Subject> findAllSubjects(){
        return subjectRepository.findAll();
    }

    public List<Subject> findSubjectByMemberId(Long memberId) {
        return subjectRepository.findByMemberId(memberId);
    }
}
