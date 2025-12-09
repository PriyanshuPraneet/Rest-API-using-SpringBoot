package com.socialmedia.restfulwebservices.restapi.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia.restfulwebservices.restapi.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
