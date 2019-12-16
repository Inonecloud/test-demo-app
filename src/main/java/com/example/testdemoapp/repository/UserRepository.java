package com.example.testdemoapp.repository;

import com.example.testdemoapp.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    User findByUsernameAndEmail(String username, String email);

    @EntityGraph(attributePaths = "authorities")
    List<User> findAll();

    @EntityGraph(attributePaths = "authorities")
    User findUserByEmail(String email);

    @EntityGraph(attributePaths = "authorities")
    User findUserByUsername(String username);

    User findUserById(Long id);


}
