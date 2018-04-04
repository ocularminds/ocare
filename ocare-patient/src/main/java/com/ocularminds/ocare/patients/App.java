package com.ocularminds.ocare.patients;

import com.ocularminds.ocare.patients.model.Ward;
import com.ocularminds.ocare.patients.service.RoomService;
import com.ocularminds.ocare.patients.service.RoomServiceImpl;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class App {
    
    @Autowired
    RoomServiceImpl roomService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    public void initApplication() throws IOException {
        Ward defaultz = new Ward("General Ward");
        roomService.save(defaultz);
        //booksRepo.addBook(new Book("111-1","Java 8 Lamdas","Richard Warburton"));
        //booksRepo.addBook(new Book("111-2","An Introduction to Programming in Go","Caleb Doxsey"));
    }

}
