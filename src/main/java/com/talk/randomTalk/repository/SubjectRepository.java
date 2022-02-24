package com.talk.randomTalk.repository;

import com.talk.randomTalk.domain.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Subject> findAll(){
        return em.createQuery("select s from Subject s", Subject.class)
                .getResultList();
    }
}
