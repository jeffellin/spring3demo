package com.example.blogapp.repository;

import com.example.blogapp.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface PostRepository  extends ListCrudRepository<Post,Integer> {

}
