package kr.ac.mjc.blog.service;

import kr.ac.mjc.blog.domain.Category;
import kr.ac.mjc.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategoryList(){
        return categoryRepository.findAll();
    }
}
