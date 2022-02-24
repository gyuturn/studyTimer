package com.talk.randomTalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Subject {

    @GeneratedValue
    @Id
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_name")
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    //생성 메서드//
    public static Subject createSubject(String subjectName,Member member) {
        Subject subject = new Subject();
        subject.setName(subjectName);
        subject.setMember(member);

        return subject;
    }

    //연관관계 메서드//
    public void setMember(Member member) {
        this.member=member;
        member.getSubjects().add(this);
    }

}
