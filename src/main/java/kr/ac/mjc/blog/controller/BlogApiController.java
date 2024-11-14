package kr.ac.mjc.blog.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.dto.ArticleDto;
import kr.ac.mjc.blog.dto.ArticleResponseDto;
import kr.ac.mjc.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogApiController {

    @Autowired
    BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity writeArticle(@RequestBody ArticleDto articleDto, HttpSession session){
        String loginUserId= (String) session.getAttribute("loginUserId");
        ArticleResponseDto response=new ArticleResponseDto();
        if(loginUserId==null){  //로그인 되어있지 않을경우
            response.setSuccess(false);

        }
        else{   //로그인 되어있을경우
            Article article=blogService.writeArticle(articleDto,loginUserId);
            if(article!=null){
                response.setSuccess(true);
                response.setArticle(article);
            }
        }


        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponseDto> deleteArticle(@PathVariable("id") long id){
        boolean success= blogService.deleteArticle(id);
        ArticleResponseDto response=new ArticleResponseDto();
        response.setSuccess(success);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponseDto> modifyArticle(@PathVariable("id") long id,
                                                            @RequestBody ArticleDto articleDto
                                                            ){
        Article article=blogService.modifyArticle(id,articleDto);
        ArticleResponseDto response=new ArticleResponseDto();
        if(article==null){  //글수정이 안됬을시
            response.setSuccess(false);
        }
        else{   //글수정 완료시
            response.setSuccess(true);
            response.setArticle(article);
        }
        return ResponseEntity.ok(response);
    }
}
