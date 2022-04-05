package com.fis.java.finaltest.repo;

import com.fis.java.finaltest.entity.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PersonRepo personRepo;

    @Test
    void givenPersonUsername_whenFindByUsername_thenReturnPersonObject() {
       Person person = personRepo.findByUsername("sa");

       assertThat(person.getId()).isEqualTo(2L);
    }
}