package com.amitthk.liquimov.model.persistence;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class GitHubRepositoryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_info_id")
    private ProjectInfo projectInfo;

    // Other GitHub repository-related fields, getters, setters

    // Other methods, constructors, toString, etc.
}

