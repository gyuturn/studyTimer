package com.talk.randomTalk.controller;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    @NotNull
    private String id;
    @NotNull
    private String password;
    @NotNull
    private String name;
    private String email;
}
