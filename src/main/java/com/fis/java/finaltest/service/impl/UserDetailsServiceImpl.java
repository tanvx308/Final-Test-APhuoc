package com.fis.java.finaltest.service.impl;

import com.fis.java.finaltest.dto.MyUserDetails;
import com.fis.java.finaltest.entity.Detective;
import com.fis.java.finaltest.entity.Person;
import com.fis.java.finaltest.service.DetectiveService;
import com.fis.java.finaltest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.findByUsername(username);
        if(person == null){
            throw new UsernameNotFoundException("Could not find.");
        }
        return new MyUserDetails(person);
    }
}
