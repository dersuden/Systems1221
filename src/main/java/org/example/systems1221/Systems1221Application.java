package org.example.systems1221;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Systems1221Application {

    public static void main(String[] args) {
        SpringApplication.run(Systems1221Application.class, args);
        AddFood food = new AddFood();
        AddUser user = new AddUser();
    }
}