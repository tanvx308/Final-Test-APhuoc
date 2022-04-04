package com.fis.java.finaltest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "evidence")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evidence extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "case_id")
    private CriminalCase criminalCase;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;

    private String number;

    private String itemName;

    private String notes;

    private Boolean archived;

    @OneToMany(mappedBy = "evidence", fetch = FetchType.LAZY)
    private Set<TrackEntry> trackEntries;
}
