package com.fis.java.finaltest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "criminal_case")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriminalCase extends AbstractEntity{
    public enum CaseType{
        UNCATEGORIZED, INFRACTION, MISDEMEANOR, FELONY
    }
    public enum CaseStatus{
        SUBMITTED, UNDER_INVESTIGATION, IN_COURT, CLOSED, DISMISSED, COLD
    }

    private String number;

    @Enumerated(EnumType.STRING)
    private CaseType type;

    private String shortDescription;

    private String detailedDescription;

    @Enumerated(EnumType.STRING)
    private CaseStatus status;

    private String notes;

    @OneToMany(mappedBy = "criminalCase", fetch = FetchType.LAZY)
    private Set<Evidence> evidenceSet;

    @ManyToOne
    @JoinColumn(name = "lead_investigator")
    private Detective leadInvestigator;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "working_detective_case",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "detective_id")
    )
    private Set<Detective> assigned;
}
