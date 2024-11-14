package kr.ac.mjc.blog.repository;

import kr.ac.mjc.blog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
