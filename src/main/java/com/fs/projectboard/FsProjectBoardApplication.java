package com.fs.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class FsProjectBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(FsProjectBoardApplication.class, args);
    }

}
