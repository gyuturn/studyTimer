package com.talk.randomTalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Article {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    private String writer;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;


    //생성메서드
    public static Article createArticle(String title, String content, Member member,String writer) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setMember(member);
        article.setWriter(writer);

        return article;
    }

}
