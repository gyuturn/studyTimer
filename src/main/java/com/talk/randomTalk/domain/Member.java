package com.talk.randomTalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    private LocalTime totalTime= LocalTime.of(00, 00, 00);

    private String eMail;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();

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
