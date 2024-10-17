package kr.ac.mjc.blog.service;

import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.dto.ArticleDto;
import kr.ac.mjc.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    public ArrayList<Article> getArticleList(){
        List<Article> list=blogRepository.findAll();
        return (ArrayList<Article>) list;
    }

    public Article writeArticle(ArticleDto articleDto){
        Article article=new Article(articleDto.getTitle(),articleDto.getContent());
        article=blogRepository.save(article);
        return article;
    }

}
