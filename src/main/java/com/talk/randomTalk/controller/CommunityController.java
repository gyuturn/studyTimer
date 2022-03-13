package com.talk.randomTalk.controller;

import com.talk.randomTalk.domain.Article;
import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.form.LoginForm;
import com.talk.randomTalk.form.WriteForm;
import com.talk.randomTalk.repository.ArticleRepository;
import com.talk.randomTalk.repository.MemberRepository;
import com.talk.randomTalk.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ArticleRepository articleRepository;
    @GetMapping("community/home")
    public String home(){

        return "community/home";
    }

    @GetMapping("community/write")
    public String write(Model model){
        model.addAttribute("WriteForm",new WriteForm());
        return "community/write";
    }

    @PostMapping("community/write")
    public String postWrite(@Validated @ModelAttribute("WriteForm") WriteForm writeForm,
                            BindingResult result, HttpServletRequest request){
        if (result.hasErrors()) {
            return "error";
        }



        Cookie[] cookies = request.getCookies();
        String memberId = cookies[0].getValue();
        List<Member> memberList = memberRepository.findById(memberId);
        Member member = memberList.get(0);
        Article article = Article.createArticle(writeForm.getTitle(), writeForm.getContent(), member);
        articleRepository.save(article);

        return "redirect:/community/home";

    }
}
