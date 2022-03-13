package com.talk.randomTalk.repository;

import com.talk.randomTalk.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional
public class ArticleRepository {
    private final EntityManager em;


    public void save(Article article) {
        em.persist(article);
    }
}
