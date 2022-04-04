package com.fis.java.finaltest.service.impl;

import com.fis.java.finaltest.entity.Person;
import com.fis.java.finaltest.exception.ResourceNotFoundException;
import com.fis.java.finaltest.repo.PersonRepo;
import com.fis.java.finaltest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepo personRepo;
    @Override
    public Person findPersonById(Long id) {
        if(!personRepo.existsById(id)){
            throw new ResourceNotFoundException("Person", "Id", String.valueOf(id));
        }
        return personRepo.getById(id);
    }

}
