package com.fis.java.finaltest.service;

import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.entity.Evidence;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CriminalCaseService {
    List<CriminalCase> findCriminalCases(Pageable pageable);

    CriminalCase saveCriminalCase(CriminalCase criminalCase);

    CriminalCase updateCriminalCase(CriminalCase criminalCase, Long id);

    void deleteCriminalCase(Long id);

    CriminalCase findCriminalCaseById(Long id);

    List<CriminalCase> findCriminalCaseByStatus(CriminalCase.CaseStatus status,Pageable pageable);

    boolean existsByNumber(String number);

    List<Evidence> findEvidenceByCriminalWithNumber(String number, Pageable pageable);

    List<CriminalCase> findByDetectiveWithUsername(String username, Pageable pageable);
}
