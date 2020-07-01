package com.rbr.phoenix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.AnsiDialect;
import org.springframework.data.relational.core.dialect.Dialect;

@SpringBootApplication
public class SpringBootPhoenixExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPhoenixExampleApplication.class, args);
    }

    @Bean
    public Dialect dialect(){
        return AnsiDialect.INSTANCE;
    }
}
