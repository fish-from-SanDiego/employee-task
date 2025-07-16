package org.fishfromsandiego.employeetask.data.repository;

import org.fishfromsandiego.employeetask.data.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findById(long id);

    List<Employee> findByBirthDateBetween(LocalDate from, LocalDate to);

    List<Employee> findAll();
}
