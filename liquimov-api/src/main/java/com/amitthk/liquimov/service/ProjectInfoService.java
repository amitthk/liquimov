package com.amitthk.liquimov.service;

import com.amitthk.liquimov.model.persistence.ProjectInfo;
import com.amitthk.liquimov.model.persistence.UserInfo;
import com.amitthk.liquimov.repository.ProjectInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectInfoService {
    @Autowired
    private ProjectInfoRepository projectInfoRepository;

    public ProjectInfo createProject(UserInfo userInfo, ProjectInfo projectInfo) {
        projectInfo.setUserInfo(userInfo);
        return projectInfoRepository.save(projectInfo);
    }


}
