--liquibase formatted sql

--changeset fishFromSanDiego:1.1_create_departments_table
CREATE TABLE departments
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);
--rollback DROP TABLE departments;

--changeset fishFromSanDiego:1.2_create_employees_table
CREATE TABLE employees
(
    id            BIGSERIAL PRIMARY KEY,
    first_name    TEXT           NOT NULL,
    last_name     TEXT           NOT NULL,
    birth_date    DATE           NOT NULL
        CONSTRAINT check_birth_date_before_now CHECK (birth_date <= CURRENT_DATE),
    department_id BIGINT         NOT NULL,
    salary_rub    NUMERIC(13, 2) NOT NULL
        CONSTRAINT check_positive_salary CHECK ( salary_rub > 0 ),
    CONSTRAINT fk_department FOREIGN KEY (department_id)
        REFERENCES departments (id)
        ON DELETE RESTRICT
);

--rollback DROP TABLE employees;