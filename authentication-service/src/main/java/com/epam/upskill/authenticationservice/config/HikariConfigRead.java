package com.epam.upskill.authenticationservice.config;

import com.zaxxer.hikari.HikariConfig;

import java.util.Properties;

public class HikariConfigRead extends HikariConfig {

    protected final HikariReadProperties hikariReadProperties;

    protected final String PERSISTENCE_UNIT_NAME = "read";


    protected HikariConfigRead(HikariReadProperties hikariReadProperties) {
        this.hikariReadProperties = hikariReadProperties;
        setPoolName(this.hikariReadProperties.getPoolName());
        setMinimumIdle(this.hikariReadProperties.getMinimumIdle());
        setMaximumPoolSize(this.hikariReadProperties.getMaximumPoolSize());
        setIdleTimeout(this.hikariReadProperties.getIdleTimeout());
    }
}
