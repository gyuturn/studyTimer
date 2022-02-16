package com.talk.randomTalk.service;

import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional(readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberId();
    }

    //중복회원 조회
    private void validateDuplicateMember(Member member) {
        //예외 터트리기
        List<Member> findMembers = memberRepository.findById(member.getId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 사용중인 아이디입니다.");
        }
    }
}
