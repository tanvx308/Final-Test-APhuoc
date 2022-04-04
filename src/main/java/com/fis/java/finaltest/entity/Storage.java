package com.fis.java.finaltest.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "storage")
public class Storage extends AbstractEntity{
    private String name;

    private String location;

    @OneToMany(mappedBy = "storage", fetch = FetchType.LAZY)
    private Set<Evidence> evidenceSet;
}
