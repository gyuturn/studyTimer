package com.talk.randomTalk.repository;

import com.talk.randomTalk.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long membeId) {
        return em.find(Member.class, membeId);
    }

    public List<Member> findById(String id) {
        return em.createQuery("select m from Member m where m.id=:id", Member.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

}
