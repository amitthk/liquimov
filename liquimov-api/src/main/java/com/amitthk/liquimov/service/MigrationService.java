package com.amitthk.liquimov.service;

import com.amitthk.liquimov.config.DataSourceConfig;
import com.amitthk.liquimov.model.ConnectionDetails;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class MigrationService {

    private final DataSourceConfig dataSourceConfig;

    // Constructor Injection
    public MigrationService(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public boolean performMigration(ConnectionDetails details) {
        try {
            DataSource dataSource = dataSourceConfig.createDataSource(details.getDriverClassName(), details.getUrl(), details.getUsername(), details.getPassword());
            SpringLiquibase liquibase = new SpringLiquibase();
            liquibase.setDataSource(dataSource);
            liquibase.setChangeLog("classpath:/db/changelog/db.changelog-master.yaml");
            liquibase.afterPropertiesSet(); // Start the migration
            return true;
        } catch (Exception e) {
            // Log the exception
            return false;
        }
    }

    public boolean performRollback(ConnectionDetails details) {
        // Similar implementation as migrate, using Liquibase's rollback capabilities
        return false; // Placeholder
    }
}
