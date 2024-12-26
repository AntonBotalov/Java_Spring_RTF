package ru.botalov.test_security2db_themeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.botalov.test_security2db_themeleaf.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
