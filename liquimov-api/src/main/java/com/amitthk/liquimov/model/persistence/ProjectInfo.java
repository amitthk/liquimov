package com.amitthk.liquimov.model.persistence;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ProjectInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "projectInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConnectionInfo> connectionInfos = new ArrayList<>();

    @OneToMany(mappedBy = "projectInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GitHubRepositoryInfo> gitHubRepositoryInfos = new ArrayList<>();

    // Other project-related fields, getters, setters

    // Other methods, constructors, toString, etc.
}

