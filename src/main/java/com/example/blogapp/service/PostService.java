package com.example.blogapp.service;

import com.example.blogapp.model.Post;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@Service
public class PostService {

    private final RestTemplate restTemplate;
    private final ObservationRegistry registry;

    public PostService(RestTemplate restTemplate, ObservationRegistry registry) {
        this.restTemplate = restTemplate;
        this.registry = registry;
    }

    public List<Post> loadPosts() {

        List<Post> posts = Observation.createNotStarted("blogapplication.loadposts", registry)
                .lowCardinalityKeyValue("some-value", "200")
                .observe(() -> {
                    ResponseEntity<List<Post>> exchange = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
                    });
                    return exchange.getBody();
                });


        return posts;

    }

}
