package com.in28minutes.restful.web.services.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.restful.web.services.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}