package com.epam.upskill.authenticationservice.config;

import com.zaxxer.hikari.HikariConfig;

import java.util.Properties;

public class HikariConfigWrite extends HikariConfig {

    protected final HikariWriteProperties hikariWriteProperties;

    protected final String PERSISTENCE_UNIT_NAME = "write";



    protected HikariConfigWrite(HikariWriteProperties hikariWriteProperties) {
        this.hikariWriteProperties = hikariWriteProperties;
        setPoolName(this.hikariWriteProperties.getPoolName());
        setMinimumIdle(this.hikariWriteProperties.getMinimumIdle());
        setMaximumPoolSize(this.hikariWriteProperties.getMaximumPoolSize());
        setIdleTimeout(this.hikariWriteProperties.getIdleTimeout());
    }
}
