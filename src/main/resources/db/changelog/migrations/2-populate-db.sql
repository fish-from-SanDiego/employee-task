--liquibase formatted sql

--changeset fishFromSanDiego:2.1_insert_into_departments
INSERT INTO departments (name)
VALUES ('IT'),
       ('Sales'),
       ('HR'),
       ('Management')
ON CONFLICT (name) DO NOTHING;

--changeset fishFromSanDiego:2.2_insert_into_employees
INSERT INTO employees (first_name, last_name, birth_date, department_id, salary_rub)
SELECT 'Ivan', 'Ivanov', TO_DATE('1990-01-01', 'YYYY-MM-DD'), d.id, 100000.00
FROM departments d
WHERE d.name = 'IT'
UNION ALL
SELECT 'Ivan', 'Smirnov', TO_DATE('2000-08-27', 'YYYY-MM-DD'), d.id, 135000.00
FROM departments d
WHERE d.name = 'Sales'
UNION ALL
SELECT 'John', 'Doe', TO_DATE('1992-12-10', 'YYYY-MM-DD'), d.id, 354698.00
FROM departments d
WHERE d.name = 'HR'
UNION ALL
SELECT 'Anna', 'Petrova', TO_DATE('2003-07-15', 'YYYY-MM-DD'), d.id, 99568.00
FROM departments d
WHERE d.name = 'HR'
UNION ALL
SELECT 'John', 'Smith', TO_DATE('2000-08-27', 'YYYY-MM-DD'), d.id, 135000.00
FROM departments d
WHERE d.name = 'Sales'
UNION ALL
SELECT 'Anna', 'Smith', TO_DATE('1991-05-05', 'YYYY-MM-DD'), d.id, 100.00
FROM departments d
WHERE d.name = 'IT'
UNION ALL
SELECT 'Peter', 'Nikiforov', TO_DATE('1990-11-01', 'YYYY-MM-DD'), d.id, 777000.00
FROM departments d
WHERE d.name = 'IT'
UNION ALL
SELECT 'Hu', 'Tao', TO_DATE('2002-04-11', 'YYYY-MM-DD'), d.id, 35023.97
FROM departments d
WHERE d.name = 'Sales'
UNION ALL
SELECT 'Oleg', 'Kashin', TO_DATE('1980-06-17', 'YYYY-MM-DD'), d.id, 1000000.00
FROM departments d
WHERE d.name = 'Management';
