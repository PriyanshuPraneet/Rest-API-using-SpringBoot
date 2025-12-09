package com.socialmedia.restfulwebservices.restapi.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia.restfulwebservices.restapi.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
