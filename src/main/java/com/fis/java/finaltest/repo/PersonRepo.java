package com.fis.java.finaltest.repo;

import com.fis.java.finaltest.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
}
