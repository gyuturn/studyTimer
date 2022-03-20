package com.talk.randomTalk.service;

import com.talk.randomTalk.domain.Article;
import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.repository.ArticleRepository;
import com.talk.randomTalk.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleService articleService;
    @Test
    @DisplayName("글의 memberId를 가지고 member 들고오기")
    public void 글의외래키들고오기() {
        //given
        Member member = Member.createMember("test1111", "1111", "testFor111", "");
        memberRepository.save(member);
        Article article = Article.createArticle("ho", "hello", member, member.getName());
        articleRepository.save(article);
        //when
        Member memberByArticleId = articleService.findMemberByArticleId(article.getId());
        //then
        Assertions.assertThat(memberByArticleId).isEqualTo(member);



    }
}