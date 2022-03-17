package com.talk.randomTalk.repository;

import com.talk.randomTalk.domain.Article;
import com.talk.randomTalk.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ArticleRepository {
    private final EntityManager em;


    public void save(Article article) {
        em.persist(article);
    }

    public List<Article> findAll() {
        return em.createQuery("select a from Article a", Article.class)
                .getResultList();
    }

    public Article findById(Long id) {
        return em.find(Article.class,id);
    }
}
