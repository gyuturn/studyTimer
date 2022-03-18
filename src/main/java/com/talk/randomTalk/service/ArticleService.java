package com.talk.randomTalk.service;

import com.talk.randomTalk.domain.Article;
import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.repository.ArticleRepository;
import com.talk.randomTalk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    //article id를 통해 memberId 찾기
    public Member findMemberByArticleId(Long articleId) {
        Article article = articleRepository.findById(articleId);
        Member member = memberRepository.findOne(article.getMember().getMemberId());
        return member;

    }
}


