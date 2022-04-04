package com.fis.java.finaltest.dto;

import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.entity.Storage;
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
public class EvidenceDto {
    @NotEmpty(message = "Number should not be null.")
    private String number;

    @NotEmpty(message = "Item name should not be null.")
    private String itemName;

    @NotEmpty(message = "Notes should not be null.")
    private String notes;

    @NotNull(message = "Archived should not be null.")
    private Boolean archived;

    private Storage storage;

    private CriminalCase criminalCase;

    private Set<TrackEntry> trackEntries;
}
