package org.example.systems1221;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class Systems1221Application {

    public static void main(String[] args) {
        SpringApplication.run(Systems1221Application.class, args);
        Food food = new Food();
        food.caloriesOfTheDay(Date.valueOf("2025-03-24"));
    }
}