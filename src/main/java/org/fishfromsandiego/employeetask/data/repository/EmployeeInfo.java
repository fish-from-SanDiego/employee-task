package org.fishfromsandiego.employeetask.data.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface EmployeeInfo {
    long getId();

    String getFirstName();

    String getLastName();

    LocalDate getBirthDate();

    BigDecimal getSalaryRubles();

    long getDepartmentId();

    String getDepartmentName();
}
