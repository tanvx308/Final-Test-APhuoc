package com.fis.java.finaltest.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "track_entry")
public class TrackEntry extends AbstractEntity{
    enum TrackAction{
        SUBMITTED, RETRIEVED, RETURNED
    }
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "evidence_id")
    private Evidence evidence;

    @ManyToOne
    @JoinColumn(name = "detective_id")
    private Detective detective;

    @Enumerated(EnumType.STRING)
    private TrackAction action;

    private String reason;
}
