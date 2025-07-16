package org.fishfromsandiego.employeetask;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class EmployeeTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeTaskApplication.class, args);
    }
}
