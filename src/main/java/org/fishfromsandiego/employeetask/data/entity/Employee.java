package org.fishfromsandiego.employeetask.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Builder.Default
    private long id = 0;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "salary_rub", nullable = false, precision = 13, scale = 2)
    private BigDecimal salaryRubles;

    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Department department;
}
