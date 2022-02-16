package com.talk.randomTalk.controller;

import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("login")
    public String login() {
        return "member/login";
    }

    @GetMapping("signUp")
    public String signUp(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/signUp";
    }

    @PostMapping("signUp")
    public String signUp(@Validated MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()) {
            return "member/signUp";
        }
        Member member = new Member();
        member.setId(memberForm.getId());
        member.setName(memberForm.getName());
        member.setPassword(memberForm.getPassword());
        member.setEMail(memberForm.getEmail());

        memberService.join(member);

        return "redirect:/";
    }

    @PostMapping("login/process")
    public String loginAuthentication(Model model) {

        return "redirect:/";

    }
}
