package kr.ac.mjc.blog.controller;

import kr.ac.mjc.blog.domain.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(Model model){
        Article article=new Article();
        article.setId(1l);
        article.setTitle("첫번째 포스팅");
        article.setContent("첫번째글입니다");

        ArrayList<String> categoryList=new ArrayList();
        categoryList.add("취미");
        categoryList.add("전공");
        categoryList.add("정보");

        article.setCategoryList(categoryList);

        model.addAttribute("article",article);
        return "test";
    }

    @GetMapping("/list1")
    public ModelAndView list1(){
        ArrayList<Article> list=new ArrayList();
        for(int i=0;i<100;i++){
            Article article=new Article("제목"+i,"내용"+i);
            list.add(article);
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("list1");
        return modelAndView;
    }
    @GetMapping("/list2")
    public ModelAndView list2(){
        ArrayList<Article> list=new ArrayList();
        for(int i=0;i<100;i++){
            Article article=new Article("제목"+i,"내용"+i);
            list.add(article);
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("list2");
        return modelAndView;
    }

}
