package com.amitthk.liquimov.model.persistence;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class ConnectionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_info_id")
    private ProjectInfo projectInfo;

    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
