package kr.ac.mjc.blog.service;

import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.dto.ArticleDto;
import kr.ac.mjc.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        System.out.println(article.getTitle());
        System.out.println(article.getContent());
        article=blogRepository.save(article);
        return article;
    }


    public Article getArticleItem(long id){
        Optional<Article> result=blogRepository.findById(id);
        //결과값이 비어있을때
        if(result.isEmpty()){
            return null;
        }
        //결과값이 있을때
        else{
            return result.get();
        }
        
    }

}
