-- Create the student table if it doesn't already exist
DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT FROM information_schema.tables
        WHERE table_name = 'student'
    ) THEN
CREATE TABLE student (
    id            BIGINT PRIMARY KEY,    -- Unique identifier for each student
    name          VARCHAR(100) NOT NULL, -- Name of the student
    date_of_birth DATE         NOT NULL, -- Date of birth of the student
    joining_date  DATE         NOT NULL, -- Date when the student joined
    course        VARCHAR(50)  NOT NULL  -- Course the student belongs to
);
    END IF;
END
$$;

-- Create sequence for student id generation if not exists
CREATE SEQUENCE IF NOT EXISTS student_seq
    AS BIGINT
    INCREMENT BY 1
    OWNED BY student.id
