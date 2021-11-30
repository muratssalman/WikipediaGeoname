package org.wikipediasearch.geonameswikipediasearchserviceapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

            logger.info("Before Starting application");
            logger.debug("Starting my application in debug with {} arguments", args.length);
            logger.info("Starting my application with {} arguments.", args.length);

            SpringApplication.run(App.class, args);
        }
    }
