package com;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Application implements CommandLineRunner {

    static {
        //The language of Java is set in English
        Locale.setDefault(new Locale("en", "EN"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
    }

}