package com.shevaz.searchblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SearchBlogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchBlogApiApplication.class, args);
    }

}
