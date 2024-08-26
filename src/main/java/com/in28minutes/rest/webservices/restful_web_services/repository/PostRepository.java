package com.in28minutes.rest.webservices.restful_web_services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.rest.webservices.restful_web_services.User.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {

    
} 
