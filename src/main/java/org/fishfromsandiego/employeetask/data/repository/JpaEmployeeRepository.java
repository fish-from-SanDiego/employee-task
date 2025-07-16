package org.fishfromsandiego.employeetask.data.repository;

import jakarta.annotation.Nonnull;
import org.fishfromsandiego.employeetask.data.entity.Department;
import org.fishfromsandiego.employeetask.data.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepository {

    @Query(value =
            "SELECT e.id as id, e.first_name as firstName, e.last_name as lastName," +
                    " e.birth_date as birthDate, e.salary_rub as salaryRubles," +
                    " d.id as departmentId, d.name as departmentName " +
                    "FROM employees e " +
                    "JOIN departments d ON e.department_id=d.id " +
                    "WHERE e.id = :id",
            nativeQuery = true)
    Optional<EmployeeInfo> findByIdRaw(@Param("id") long id);

    @Query(value =
            "SELECT e.id as id, e.first_name as firstName, e.last_name as lastName," +
                    " e.birth_date as birthDate, e.salary_rub as salaryRubles," +
                    " d.id as departmentId, d.name as departmentName " +
                    "FROM employees e " +
                    "JOIN departments d ON e.department_id=d.id " +
                    "WHERE e.birth_date >= :from AND e.birth_date <= :to",
            nativeQuery = true)
    List<EmployeeInfo> findByBirthDateBetweenRaw(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query(value =
            "SELECT e.id as id, e.first_name as firstName, e.last_name as lastName," +
                    " e.birth_date as birthDate, e.salary_rub as salaryRubles," +
                    " d.id as departmentId, d.name as departmentName " +
                    "FROM employees e " +
                    "JOIN departments d ON e.department_id=d.id",
            nativeQuery = true)
    List<EmployeeInfo> findAllRaw();

    @Override
    @Nonnull
    default List<Employee> findAll() {
        return findAllRaw().stream().map(this::getFromFetchResult).toList();
    }

    @Override
    default Optional<Employee> findById(long id) {
        return findByIdRaw(id).map(this::getFromFetchResult);
    }

    @Override
    default List<Employee> findByBirthDateBetween(LocalDate from, LocalDate to) {
        return findByBirthDateBetweenRaw(from, to).stream().map(this::getFromFetchResult).toList();
    }


    private Employee getFromFetchResult(EmployeeInfo resultRow) {
        return Employee.builder()
                .id(resultRow.getId())
                .firstName(resultRow.getFirstName())
                .lastName(resultRow.getLastName())
                .birthDate(resultRow.getBirthDate())
                .salaryRubles(resultRow.getSalaryRubles())
                .department(
                        Department.builder()
                                .id(resultRow.getDepartmentId())
                                .name(resultRow.getDepartmentName())
                                .build())
                .build();
    }
}
