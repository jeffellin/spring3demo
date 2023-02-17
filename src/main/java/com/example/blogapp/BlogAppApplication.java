package com.example.blogapp;

import com.example.blogapp.repository.PostRepository;
import com.example.blogapp.service.JsonPostsService;
import com.example.blogapp.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class BlogAppApplication {

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}


	@Bean
	JsonPostsService jsonPostsService(){
		WebClient client = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
		JsonPostsService jps = factory.createClient(JsonPostsService.class);
		return jps;
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
