package com.acme.mindflicks.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MindflicksPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindflicksPlatformApplication.class, args);
    }

}
