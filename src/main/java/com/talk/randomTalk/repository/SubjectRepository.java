package com.talk.randomTalk.repository;

import com.talk.randomTalk.domain.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class SubjectRepository {

    private final EntityManager em;

    public void save(Subject subject) {
        em.persist(subject);
    }

    public Subject findOne(Long subjectId) {
        return em.find(Subject.class, subjectId);
    }
}
