package com.fis.java.finaltest.service;

import com.fis.java.finaltest.entity.Evidence;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface EvidenceService {
    List<Evidence> findEvidences(Pageable pageable);

    Evidence createEvidence(Evidence evidence);

    Evidence updateEvidence(Evidence evidence, Long id);

    void deleteEvidence(Long id);

    Evidence findEvidenceById(Long id);

}
