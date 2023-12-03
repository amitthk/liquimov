package com.amitthk.liquimov.controller;

import com.amitthk.liquimov.model.ConnectionDetails;
import com.amitthk.liquimov.service.MigrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/migration")
public class MigrationController {

    private final MigrationService migrationService;

    // Constructor Injection
    public MigrationController(MigrationService migrationService) {
        this.migrationService = migrationService;
    }

    @PostMapping("/migrate")
    public ResponseEntity<String> migrate(@RequestBody ConnectionDetails details) {
        boolean success = migrationService.performMigration(details);
        return ResponseEntity.ok(success ? "Migration Successful" : "Migration Failed");
    }

    @PostMapping("/rollback")
    public ResponseEntity<String> rollback(@RequestBody ConnectionDetails details) {
        boolean success = migrationService.performRollback(details);
        return ResponseEntity.ok(success ? "Rollback Successful" : "Rollback Failed");
    }
}
