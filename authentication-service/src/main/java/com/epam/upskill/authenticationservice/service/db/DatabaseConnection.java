package com.epam.upskill.authenticationservice.service.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @description: TODO
 * @date: 02 February 2024 $
 * @time: 5:55 PM 08 $
 * @author: Qudratjon Komilov
 */


@Component
public class DatabaseConnection {

    @Value("${spring.datasource-user.jdbc-url}")
    private String databaseUrl;

    @Value("${spring.datasource-user.username}")
    private String username;

    @Value("${spring.datasource-user.password}")
    private String password;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, username, password);
    }
}


