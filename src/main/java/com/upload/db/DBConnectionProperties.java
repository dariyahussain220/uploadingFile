

package com.upload.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBConnectionProperties {

    private final String host, port, user, password;

    @Autowired
    public DBConnectionProperties() {
        host = System.getenv().getOrDefault("db_host", "localhost");
        port = System.getenv().getOrDefault("db_port", "3306");
        user = System.getenv().getOrDefault("db_user", "root");
        password = System.getenv().getOrDefault("db_password", "Admin@123");
    }

    public String host() {
        return host;
    }

    public String port() {
        return port;
    }

    public String user() {
        return user;
    }

    public String password() {
        return password;
    }
}