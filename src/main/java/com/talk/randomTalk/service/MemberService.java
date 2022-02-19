package com.talk.randomTalk.service;

import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.form.LoginForm;
import com.talk.randomTalk.form.MemberForm;
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
        validateDuplicateMemberForId(member);
        validateDuplicateMemberForName(member);
        memberRepository.save(member);
        return member.getMemberId();
    }

    //id와 비밀번호 같은지 조회
    public boolean validLogin(LoginForm loginForm) {
        List<Member> listId = memberRepository.findById(loginForm.getId());
        if (listId.isEmpty()) {
            throw new IllegalStateException("일치하는 아이디가 없습니다.");
        }
        else if (listId.size() == 1) {
            Member member = listId.get(0);

            if (member.getPassword().equals(loginForm.getPassword())) {
                return true;
            }
        }
        else {
            throw new IllegalStateException("아이디가 여러개 중복 조회됩니다??");
        }
        return false;

    }

    //중복회원 조회
    private void validateDuplicateMemberForId(Member member) {
        //예외 터트리기
        List<Member> findMembers = memberRepository.findById(member.getId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 사용중인 아이디입니다.");
        }
    }

    //중복이름 조회
    private void validateDuplicateMemberForName(Member member) {
        //예외 터트리기
        List<Member> findMembers = memberRepository.findById(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 사용중인 이름입니다.");
        }
    }


}
