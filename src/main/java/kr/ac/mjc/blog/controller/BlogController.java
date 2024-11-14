package kr.ac.mjc.blog.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.domain.Category;
import kr.ac.mjc.blog.domain.User;
import kr.ac.mjc.blog.dto.ArticleDto;
import kr.ac.mjc.blog.service.BlogService;
import kr.ac.mjc.blog.service.CategoryService;
import kr.ac.mjc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ModelAndView main(HttpSession session){
        ModelAndView mav=new ModelAndView();
        ArrayList<Article> articles= blogService.getArticleList();
        if(session.getAttribute("loginUserId")!=null){  //로그인된 사용자가 있을경우
            String loginUserId= (String) session.getAttribute("loginUserId");
            User loginUser=userService.getUserInfo(loginUserId);
            mav.addObject("loginUser",loginUser);
        }
        mav.addObject("articles",articles);
        mav.setViewName("main");
        return mav;
    }
    @GetMapping("/write")
    public ModelAndView write(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("write");
        List<Category> categoryList=categoryService.getCategoryList();
        mav.addObject("categoryList",categoryList);
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

    @GetMapping("/login")
    public String loginView(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUserId");
        return "redirect:/";
    }

}
