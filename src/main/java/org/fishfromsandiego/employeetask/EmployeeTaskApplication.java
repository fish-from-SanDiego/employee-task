package org.fishfromsandiego.employeetask;

import lombok.RequiredArgsConstructor;
import org.fishfromsandiego.employeetask.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class EmployeeTaskApplication {
    private final EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeTaskApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady(ApplicationReadyEvent event) {
        System.out.println(employeeService.findById(-1));
    }
}
