package com.talk.randomTalk.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @GeneratedValue
    @Id
    @Column(name = "member_id")
    private Long memberId;

    private String id;

    private String password;

    private String name;

    private String eMail;





}
