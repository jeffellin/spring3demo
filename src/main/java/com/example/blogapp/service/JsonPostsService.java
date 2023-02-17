package com.example.blogapp.service;

import com.example.blogapp.model.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;


public interface JsonPostsService {
    @GetExchange("/posts")
    List<Post> loadPosts();
}
