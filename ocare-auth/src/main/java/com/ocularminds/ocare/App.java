package com.ocularminds.ocare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("password"));        
        SpringApplication.run(App.class, args);
    }

}
