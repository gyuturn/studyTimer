package com.talk.randomTalk.controller;

import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.domain.Subject;
import com.talk.randomTalk.form.LoginForm;
import com.talk.randomTalk.form.MemberForm;
import com.talk.randomTalk.repository.MemberRepository;
import com.talk.randomTalk.service.MemberService;
import com.talk.randomTalk.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final SubjectService subjectService;
    private final MemberRepository memberRepository;
    @GetMapping("signUp")
    public String signUp(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/signUp";
    }

    @PostMapping("signUp")
    public String signUp(@Validated @ModelAttribute MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        Member member = new Member();
        member.setId(memberForm.getId());
        member.setName(memberForm.getName());
        member.setPassword(memberForm.getPassword());
        member.setEMail(memberForm.getEmail());

        memberService.join(member);

        return "redirect:/";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "member/login";
    }

    @PostMapping("/login")
    public String loginAuthentication(@Validated @ModelAttribute("loginForm") LoginForm loginForm,
                                      BindingResult result,
                                      HttpServletResponse response) {
        if (result.hasErrors()) {
            return "error";
        }

        //로그인 성공한 경우
        if (memberService.validLogin(loginForm)) {

            Cookie idCookie = new Cookie("memberId", String.valueOf(loginForm.getId()));
            response.addCookie(idCookie);

            return "redirect:/loginHome";
        }
        return "error";
    }

    @GetMapping("/loginHome")
    public String homeLogin( HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies.length != 1) {
            return "redirect:/";
        }
        //멤버 아이디
        String Id = cookies[0].getValue();
        List<Member> memberList = memberRepository.findById(Id);
        String name = memberList.get(0).getName();
        model.addAttribute("name", name);

        Long memberId = memberRepository.findById(Id).get(0).getMemberId();

        //과목 리스트
        List<Subject> subjects = subjectService.findSubjectByMemberId(memberId);
        model.addAttribute("subjects", subjects);

        //총 시간
        Member member = memberRepository.findById(Id).get(0);
        memberService.calcTotalTime(member, subjects);
        model.addAttribute("memberTotalTime", member.getTotalTime());
        return "member/loginHome";
    }
}
