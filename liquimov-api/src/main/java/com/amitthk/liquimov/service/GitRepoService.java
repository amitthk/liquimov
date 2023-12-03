package com.amitthk.liquimov.service;

import com.amitthk.liquimov.model.MigrationException;
import liquibase.integration.spring.SpringLiquibase;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Service
public class GitRepoService {

    @Autowired
    private SpringLiquibase liquibase;

    @Value("${github.token}")
    private String githubToken;

    public void migrateFromGitHub(String repositoryUrl) throws MigrationException {
        try {
            // Clone the GitHub repository to a local directory
            String localDirectory = "/path/to/local/directory";
            Git git = cloneRepository(repositoryUrl, localDirectory);

            // Get the migration scripts from the local directory
            String scripts = getMigrationScripts(localDirectory);

            // Apply the migration scripts using Liquibase
            liquibase.setChangeLog(scripts);
            liquibase.afterPropertiesSet();
        } catch (Exception e) {
            throw new MigrationException("Migration from GitHub repository failed: " + e.getMessage(), e);
        }
    }

    private Git cloneRepository(String repositoryUrl, String localDirectory) throws Exception {
        CloneCommand cloneCommand = Git.cloneRepository()
                .setURI(repositoryUrl)
                .setDirectory(Paths.get(localDirectory).toFile()); // Updated to Paths.get

        // If the repository is private, set the credentials
        if (!githubToken.isEmpty()) {
            cloneCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(githubToken, ""));
        }

        return cloneCommand.call();
    }

    private String getMigrationScripts(String localDirectory) throws IOException {
        // Read and concatenate all migration scripts from the local directory
        Path migrationPath = Paths.get(localDirectory, "path", "to", "migrations");
        return Files.walk(migrationPath)
                .filter(Files::isRegularFile)
                .map(this::readFileToString)
                .collect(Collectors.joining("\n"));
    }

    private String readFileToString(Path path) {
        try {
            return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
