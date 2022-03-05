package com.zheye.question.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import javax.sql.DataSource;

public class DatabaseTestConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public PostgreSQLContainer<?> postgreSqlContainer() {
        return new PostgreSQLContainer<>("postgres:14-alpine").waitingFor(Wait.forListeningPort());
    }

    @Bean
    @FlywayDataSource
    public DataSource dataSource(PostgreSQLContainer<?> postgreSqlContainer) {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(postgreSqlContainer.getJdbcUrl());
        hikariConfig.setUsername(postgreSqlContainer.getUsername());
        hikariConfig.setPassword(postgreSqlContainer.getPassword());
        return new HikariDataSource(hikariConfig);
    }
}
