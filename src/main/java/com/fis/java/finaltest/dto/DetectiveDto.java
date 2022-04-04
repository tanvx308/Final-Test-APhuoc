package com.fis.java.finaltest.dto;

import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.entity.Detective;
import com.fis.java.finaltest.entity.Person;
import com.fis.java.finaltest.entity.TrackEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectiveDto {
    private Person person;

    @NotEmpty(message = "Badge Number should not be null.")
    private String badgeNumber;

    @NotNull(message = "Ranking should not be null.")
    private Detective.Rank ranking;

    @NotNull(message = "Armed should not be null.")
    private Boolean armed;

    @NotNull(message = "Employment Status should not be null.")
    private Detective.EmploymentStatus status;

    private Set<CriminalCase> criminalCases;

    private Set<TrackEntry> trackEntries;
}
