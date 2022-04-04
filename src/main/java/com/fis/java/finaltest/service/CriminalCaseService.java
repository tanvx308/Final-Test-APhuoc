package com.fis.java.finaltest.service;

import com.fis.java.finaltest.entity.CriminalCase;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CriminalCaseService {
    List<CriminalCase> findCriminalCases(Pageable pageable);

    CriminalCase saveCriminalCase(CriminalCase criminalCase);

    CriminalCase updateCriminalCase(CriminalCase criminalCase, Long id);

    void deleteCriminalCase(Long id);

    CriminalCase findCriminalCaseById(Long id);
}
