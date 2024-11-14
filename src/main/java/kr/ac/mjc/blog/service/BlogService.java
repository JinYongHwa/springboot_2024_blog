package kr.ac.mjc.blog.service;

import jakarta.transaction.Transactional;
import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.domain.Category;
import kr.ac.mjc.blog.domain.User;
import kr.ac.mjc.blog.dto.ArticleDto;
import kr.ac.mjc.blog.repository.BlogRepository;
import kr.ac.mjc.blog.repository.CategoryRepository;
import kr.ac.mjc.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ArrayList<Article> getArticleList(){
        List<Article> list=blogRepository.findAll();
        return (ArrayList<Article>) list;
    }

    public Article writeArticle(ArticleDto articleDto,String userId){
        User user= userRepository.findById(userId).get();
        Article article=new Article(articleDto.getTitle(),articleDto.getContent());
        article.setWriter(user);    //현재 로그인된 사용자를 Article의 작성자로 지정

        //선택된 categoryIds를 이용하여 카테고리 설정하기
        List<Category> categoryList=new ArrayList<>();
        for(long categoryId:articleDto.getCategoryIds()){
            Category category=categoryRepository.findById(categoryId).get();
            categoryList.add(category);
        }
        article.setCategoryList(categoryList);

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

    public boolean deleteArticle(long id){
        Article article=getArticleItem(id); //글이 있는지 확인
        if(article==null){
            return false;
        }
        blogRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Article modifyArticle(long id,ArticleDto articleDto){
        Optional<Article> result=blogRepository.findById(id);
        if(!result.isEmpty()){
            Article article=result.get();
            article.setTitle(articleDto.getTitle());
            article.setContent(articleDto.getContent());
            blogRepository.save(article);
            return article;
        }
        return null;
    }

}
