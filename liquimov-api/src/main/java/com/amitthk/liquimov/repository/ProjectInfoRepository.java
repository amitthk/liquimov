package com.amitthk.liquimov.repository;

import com.amitthk.liquimov.model.persistence.ProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectInfoRepository extends JpaRepository<ProjectInfo, Long> {
    // Define custom queries or methods here if needed
}
