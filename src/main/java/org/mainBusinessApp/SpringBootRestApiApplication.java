package org.mainBusinessApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@SpringBootApplication
public class SpringBootRestApiApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringBootRestApiApplication.class);

    public static void main(String[] args) {
        String dbPassword = System.getenv("DB_PASS");
        String dbHost = System.getenv("DB_HOST");

        if (Objects.isNull(dbPassword))
            logger.info("DB password wasn't passed, using dummy password");
        if (Objects.isNull(dbHost))
            logger.info("DB host wasn't passed, using dummy host");

        logger.info("Starting app");
        SpringApplication.run(SpringBootRestApiApplication.class, args);
    }
}
