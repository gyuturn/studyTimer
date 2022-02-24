package com.talk.randomTalk.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SubjectForm {
    @NotEmpty
    private String name;
}
