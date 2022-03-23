package com.talk.randomTalk.repository;

import com.talk.randomTalk.domain.Article;
import com.talk.randomTalk.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class CommentRepository {
    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public List<Comment> findAll() {
        return em.createQuery("select a from Comment a", Comment.class)
                .getResultList();
    }

    public Comment findById(Long id) {
        return em.find(Comment.class,id);
    }
}

