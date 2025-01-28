package com.example.portfolio.controller;

import com.example.portfolio.entity.Project;
import com.example.portfolio.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    private final ProjectService projectService;

    public HomeController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String getProjects(Model model) {
        List<Project> projects = projectService.getRepositories();

        model.addAttribute("repositories", projects);

        return "index";
    }

}