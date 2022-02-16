package com.talk.randomTalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member {

    @GeneratedValue
    @Id
    @Column(name = "member_id")
    private Long memberId;

    private String id;

    private String password;

    private String name;

    private String eMail;

    //생성 메서드//
    public static Member createMember(String id, String password, String name, String eMail) {
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setName(name);
        member.setEMail(eMail);

        return member;
    }





}
