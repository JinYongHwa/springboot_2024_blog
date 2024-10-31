package kr.ac.mjc.blog.controller;

import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.dto.ArticleDto;
import kr.ac.mjc.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/")
    public ModelAndView main(){
        ModelAndView mav=new ModelAndView();
        ArrayList<Article> articles= blogService.getArticleList();
        mav.addObject("articles",articles);
        mav.setViewName("main");
        return mav;
    }
    @GetMapping("/write")
    public String write(){
        return "write";
    }
    @PostMapping("/article")
    public ModelAndView article(@ModelAttribute ArticleDto articleDto){
        System.out.println(articleDto.getTitle());
        Article article=blogService.writeArticle(articleDto);
        ModelAndView mav=new ModelAndView();
        mav.addObject("article",article);
        mav.setViewName("redirect:/");
        return mav;
    }

    @GetMapping("/article/{id}")
    public ModelAndView getArticleItem(@PathVariable("id") long id){
        Article article=blogService.getArticleItem(id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("article",article);
        mav.setViewName("item");
        return mav;
    }
    @GetMapping("/article/modify/{id}")
    public ModelAndView modifyArticle(@PathVariable("id") long id){
        Article article=blogService.getArticleItem(id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("article",article);
        mav.setViewName("modify");
        return mav;
    }

}
