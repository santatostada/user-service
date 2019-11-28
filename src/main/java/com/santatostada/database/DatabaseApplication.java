package com.santatostada.database;

import com.santatostada.database.entity.User;
import com.santatostada.database.service.ThreadService;
import com.santatostada.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DatabaseApplication {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);

    }
}
