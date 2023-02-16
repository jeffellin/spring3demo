package com.example.blogapp;

import com.example.blogapp.repository.PostRepository;
import com.example.blogapp.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BlogAppApplication {

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	CommandLineRunner commandLineRunner(PostRepository postRepositor, PostService postService){


		return args -> {
				postRepositor.saveAll(postService.loadPosts());
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

}
