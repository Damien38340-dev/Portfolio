package com.example.portfolio.service;

import com.example.portfolio.entity.Project;
import com.example.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Value("${github.username}")
    private String githubUsername;

    private final ProjectRepository projectRepository;

    private final WebClient.Builder webClientBuilder;

    public ProjectServiceImpl(ProjectRepository projectRepository, WebClient.Builder webClientBuilder) {
        this.projectRepository = projectRepository;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public List<Project> getRepositories() {

        WebClient webClient = webClientBuilder.baseUrl("https://api.github.com").build();

        List<Project> allProjects = webClient.get()
                .uri("/users/" + githubUsername + "/repos")
                .retrieve()
                .bodyToFlux(Project.class)
                .collectList()
                .block();

        List<String> excludedProjectNames = List.of("Portfolio", "Damien38340-dev", "Dulce-website");

        assert allProjects != null;
        return allProjects.stream()
                .filter(project -> !excludedProjectNames.contains(project.getName()))
                .collect(Collectors.toList());
    }
}
