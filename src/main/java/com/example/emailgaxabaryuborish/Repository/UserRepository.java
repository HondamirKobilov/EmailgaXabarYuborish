package com.example.emailgaxabaryuborish.Repository;

import com.example.emailgaxabaryuborish.Entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
        boolean existsByUsername(String username);
    Optional<Users> findByUsernameAndEmailKod(@Email String username, String emailKod);
    boolean existsByUsernameAndEmailKod(@Email String username, String emailKod);

    Optional<Users> findByUsername(@Email String username);
}
