package org.fishfromsandiego.employeetask.service;

import lombok.RequiredArgsConstructor;
import org.fishfromsandiego.employeetask.data.entity.Employee;
import org.fishfromsandiego.employeetask.data.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findByBirthDateBetween(LocalDate from, LocalDate to) {
        return employeeRepository.findByBirthDateBetween(from, to);
    }

    public Map<String, List<Employee>> findAllGroupingByName() {
        return employeeRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getFirstName));
    }
}
