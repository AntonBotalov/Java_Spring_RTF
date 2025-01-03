package ru.botalov.test_security2db_themeleaf.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.botalov.test_security2db_themeleaf.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
