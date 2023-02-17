package com.example.blogapp.service;

import com.example.blogapp.model.Post;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostService {

    private final RestTemplate restTemplate;
    private final ObservationRegistry registry;

    private final JsonPostsService jps;

    public PostService(RestTemplate restTemplate, ObservationRegistry registry, JsonPostsService jps) {
        this.restTemplate = restTemplate;
        this.registry = registry;
        this.jps = jps;
    }

    public List<Post> loadPosts() {



        List<Post> posts = Observation
                .createNotStarted("json-place-holder.load-posts", registry)
                .lowCardinalityKeyValue("some-value", "100")
                .observe(jps::loadPosts);


        return posts;

    }

}
