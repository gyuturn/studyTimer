package com.talk.randomTalk.controller;

import com.talk.randomTalk.domain.Article;
import com.talk.randomTalk.domain.Member;
import com.talk.randomTalk.form.LoginForm;
import com.talk.randomTalk.form.WriteForm;
import com.talk.randomTalk.repository.ArticleRepository;
import com.talk.randomTalk.repository.MemberRepository;
import com.talk.randomTalk.service.ArticleService;
import com.talk.randomTalk.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final ArticleService articleService;

    @GetMapping("community/home")
    public String home(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String memberId = cookies[0].getValue();
        Member member = memberRepository.findById(memberId).get(0);
        model.addAttribute("name", member.getName());

        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);


        return "community/home";
    }

    @GetMapping("community/write")
    public String write(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String memberId = cookies[0].getValue();
        Member member = memberRepository.findById(memberId).get(0);
        model.addAttribute("name", member.getName());
        model.addAttribute("WriteForm", new WriteForm());
        return "community/write";
    }

    @PostMapping("community/write")
    public String postWrite(@Validated @ModelAttribute("WriteForm") WriteForm writeForm,
                            BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "error";
        }


        Cookie[] cookies = request.getCookies();
        String memberId = cookies[0].getValue();
        List<Member> memberList = memberRepository.findById(memberId);
        Member member = memberList.get(0);
        Article article = Article.createArticle(writeForm.getTitle(), writeForm.getContent(), member, member.getName());
        articleRepository.save(article);

        return "redirect:/community/home";

    }

    @GetMapping("community/article/{articleId}")
    public String article(@PathVariable Long articleId, Model model, HttpServletRequest request) {
        Article article = articleRepository.findById(articleId);
        ;
        model.addAttribute("article", article);

        Cookie[] cookies = request.getCookies();
        String memberId = cookies[0].getValue();
        Member member = memberRepository.findById(memberId).get(0);
        model.addAttribute("name", member.getName());

        //수정여부 가능 판단
        Member memberByArticleId = articleService.findMemberByArticleId(articleId);
        model.addAttribute("memberForFix", memberByArticleId);

        return "community/article";
    }

    @GetMapping("community/article/fix/{articleId}")
    public String fixArticle(@PathVariable Long articleId,Model model){
        Article article = articleRepository.findById(articleId);
        Member member = articleService.findMemberByArticleId(articleId);
        model.addAttribute("article", article);
        model.addAttribute("WriteForm", new WriteForm());
        model.addAttribute("name", member.getName());

        return "community/fixArticle";
    }

    @PostMapping("community/article/fix/{articleId}")
    public String postFixArticle(@PathVariable Long articleId,@Validated @ModelAttribute("WriteForm") WriteForm writeForm, BindingResult result){
        if (result.hasErrors()) {
            return"error";
        }
        Article article = articleRepository.findById(articleId);
        articleService.fixArticle(article, writeForm);

        return "redirect:/community/home";
    }
}
