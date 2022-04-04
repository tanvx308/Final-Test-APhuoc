package com.fis.java.finaltest.dto;

import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.entity.Detective;
import com.fis.java.finaltest.entity.Evidence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriminalCaseDto {
    @NotEmpty(message = "Number should not be null.")
    private String number;

    @NotNull(message = "Case Type should not be null.")
    private CriminalCase.CaseType type;

    @NotEmpty(message = "Short Description shouble not be null.")
    private String shortDescription;

    @NotEmpty(message = "Detailed Description should not be null.")
    private String detailedDescription;

    @NotNull(message = "Case Status should be not null.")
    private CriminalCase.CaseStatus status;

    @NotEmpty(message = "Notes should be not null.")
    private String notes;

    private Set<Evidence> evidenceSet;

    private Detective leadInvestigator;

    private Set<Detective> assigned;
}
