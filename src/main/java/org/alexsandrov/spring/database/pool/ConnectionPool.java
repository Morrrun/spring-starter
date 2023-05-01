package org.alexsandrov.spring.database.pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;

import java.util.List;
import java.util.Map;


public class ConnectionPool {
    private final String username;

    private final Integer poolSize;
    private final List<Object> args;
    private Map<String, Object> properties;
    public ConnectionPool(String username, Integer poolSize,
                          List<Object> args) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @PostConstruct
    private void init() {
        System.out.println("Init connection pool");
    }

    @PreDestroy
    private void destroy() {

    }

}
