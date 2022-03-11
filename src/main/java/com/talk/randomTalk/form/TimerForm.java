package com.talk.randomTalk.form;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Setter
public class TimerForm {
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime subjectTime;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime memberTime;
}
