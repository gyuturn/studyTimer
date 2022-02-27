package com.talk.randomTalk.controller;

import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.domain.Subject;
import com.talk.randomTalk.form.MemberForm;
import com.talk.randomTalk.form.SubjectForm;
import com.talk.randomTalk.repository.MemberRepository;
import com.talk.randomTalk.service.MemberService;
import com.talk.randomTalk.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final MemberRepository memberRepository;

    @GetMapping("/subject/add")
    public String addSubject(Model model) {
        model.addAttribute("subjectForm", new SubjectForm());
        return "subject/addSubject";
    }

    @PostMapping("/subject/add")
    public String addSubject(@Validated @ModelAttribute SubjectForm subjectForm, BindingResult result
    , HttpServletRequest request) {
        if (result.hasErrors()) {
            return "error";
        }
        Cookie[] cookies = request.getCookies();
        if (!(cookies.length == 1)) {
            return "error";
        }
        String id = cookies[0].getValue();
        List<Member> memberById = memberRepository.findById(id);
        Member member = memberById.get(0);

        String subjectName = subjectForm.getName();

        Long subjectId = subjectService.addSubject(member.getMemberId(), subjectName);

        return "redirect:/loginHome";
    }

//    @GetMapping("subject/timer/{id}")
//    public String timerSubject(@PathVariable Long id){
//
//
//    }
}
