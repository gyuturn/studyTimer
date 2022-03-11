package com.talk.randomTalk.repository;

import com.talk.randomTalk.domain.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Subject> findByMemberId(Long memberId) {
        return em.createQuery("select s from Subject s where s.member.memberId = :memberid ", Subject.class)
                .setParameter("memberid", memberId)
                .getResultList();
    }

    @Transactional
    public void delete(Long subjectId){
        Subject subject = em.find(Subject.class, subjectId);
        em.remove(subject);
    }
}
