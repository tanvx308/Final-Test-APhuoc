package com.fis.java.finaltest.service;

import com.fis.java.finaltest.entity.Person;
import com.fis.java.finaltest.repo.PersonRepo;
import com.fis.java.finaltest.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonRepo personRepo;

    @InjectMocks
    @Autowired
    private PersonServiceImpl personService;

    Person person;

    @BeforeEach
    public void setUp(){
        person = new Person();
        person.setId(2L);
        person.setUsername("sa");
    }

    @AfterEach
    public void tearDown(){
        person = null;
    }

    @Test
    void givenPersonId_whenFindPersonById_thenReturnPersonObject() {
        given(personRepo.findById(2L)).willReturn(Optional.of(person));

        Person expected = personService.findPersonById(person.getId());

        assertThat(expected).isEqualTo(person);
    }

    @Test
    void givenPersonUsername_whenfindByUsername_thenReturnPersonObject() {
        given(personRepo.findByUsername("sa")).willReturn(Optional.of(person).get());

        Person expected = personService.findByUsername(person.getUsername());

        assertThat(expected).isEqualTo(person);
    }
}