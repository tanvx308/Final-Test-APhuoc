package com.fis.java.finaltest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "detective")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Detective extends AbstractEntity{
    public enum Rank{
        TRAINEE, JUNIOR, SENIOR, INSPECTOR, CHIEF_INSPECTOR
    }

    public enum EmploymentStatus{
        ACTIVE, SUSPENDED, VACATION, UNDER_INVESTIGATION, RETIRED
    }
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private String badgeNumber;

    @Enumerated(EnumType.STRING)
    private Rank ranking;

    private Boolean armed;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus status;

    @ManyToMany(mappedBy = "assigned", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<CriminalCase> criminalCases;

    @OneToMany(mappedBy = "detective", fetch = FetchType.LAZY)
    private Set<TrackEntry> trackEntries;
}
