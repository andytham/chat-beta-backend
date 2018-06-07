package com.example.reactchatspring.repositories;

import com.example.reactchatspring.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u where u.username = :username")
    Optional<User> findByUsername(@Param("username")String username);

//    @Transactional
//    @Query("DELETE from User u where u.username = :username")
//    void deleteByUsername(@Param("username")String username);


    @Transactional
    Long deleteByUsername(String username);

}