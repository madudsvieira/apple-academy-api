CREATE TABLE student (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    avatar_path VARCHAR(200),
    expertise VARCHAR(255),
    active BOOLEAN DEFAULT TRUE,
    student_id VARCHAR(255),
    delivery_mode VARCHAR(255),
    program VARCHAR(255),
    birth_date DATE,
    cohort_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

ALTER TABLE student
ADD CONSTRAINT fk_student_cohort
FOREIGN KEY (cohort_id)
REFERENCES cohort(id);