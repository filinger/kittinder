package com.technoirarts.kittinder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u where :userId not member of u.likes and :userId not member of u.dislikes")
    List<User> findNotLikedOrDislikedBy(@Param("userId") Long userId);
}
