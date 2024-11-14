package kr.ac.mjc.blog.repository;

import kr.ac.mjc.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    public Optional<User> findOneByIdAndPassword(String id, String password);
}
