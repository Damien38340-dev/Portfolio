package com.example.portfolio.service;

import com.example.portfolio.entity.Project;
import com.example.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProjectService {

    @Value("${github.username}")
    private String githubUsername;

    private final ProjectRepository projectRepository;

    private final WebClient.Builder webClientBuilder;

    public ProjectService(ProjectRepository projectRepository, WebClient.Builder webClientBuilder) {
        this.projectRepository = projectRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public List<Project> getRepositories() {
        WebClient webClient = webClientBuilder.baseUrl("https://api.github.com").build();

        return webClient.get()
                .uri("/users/" + githubUsername + "/repos")
                .retrieve()
                .bodyToFlux(Project.class)
                .collectList()
                .block();
    }
}
