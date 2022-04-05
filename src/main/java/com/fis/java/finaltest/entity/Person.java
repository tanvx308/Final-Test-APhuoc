package com.fis.java.finaltest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person extends AbstractEntity{
    @Column(unique = true)
    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private LocalDateTime hiringDate;

    @OneToOne(mappedBy = "person")
    private Detective detective;
}
