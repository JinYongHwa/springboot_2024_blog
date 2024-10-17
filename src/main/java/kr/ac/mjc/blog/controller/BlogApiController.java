package kr.ac.mjc.blog.controller;

import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.dto.ArticleDto;
import kr.ac.mjc.blog.dto.ArticleResponseDto;
import kr.ac.mjc.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogApiController {

    @Autowired
    BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity writeArticle(@RequestBody ArticleDto articleDto){
        Article article=blogService.writeArticle(articleDto);
        ArticleResponseDto response=new ArticleResponseDto();
        if(article!=null){
            response.setSuccess(true);
            response.setArticle(article);
        }
        return ResponseEntity.ok(response);
    }
}
