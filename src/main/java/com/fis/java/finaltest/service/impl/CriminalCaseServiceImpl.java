package com.fis.java.finaltest.service.impl;

import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.entity.Evidence;
import com.fis.java.finaltest.exception.ResourceNotFoundException;
import com.fis.java.finaltest.repo.CriminalCaseRepo;
import com.fis.java.finaltest.service.CriminalCaseService;
import com.fis.java.finaltest.service.DetectiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriminalCaseServiceImpl implements CriminalCaseService {
    @Autowired
    private CriminalCaseRepo criminalCaseRepo;

    @Autowired
    private DetectiveService detectiveService;

    @Override
    public List<CriminalCase> findCriminalCases(Pageable pageable) {
        return criminalCaseRepo.findAll(pageable).getContent();
    }

    @Override
    public CriminalCase saveCriminalCase(CriminalCase criminalCase) {
        return criminalCaseRepo.save(criminalCase);
    }

    @Override
    public CriminalCase updateCriminalCase(CriminalCase criminalCase, Long id) {
        if(!criminalCaseRepo.existsById(id)){
            throw new ResourceNotFoundException("Criminal Case", "Id", String.valueOf(id));
        }
        criminalCase.setId(id);
        return criminalCaseRepo.save(criminalCase);
    }

    @Override
    public void deleteCriminalCase(Long id) {
        if(!criminalCaseRepo.existsById(id)){
            throw new ResourceNotFoundException("Criminal Case", "Id", String.valueOf(id));
        }
        criminalCaseRepo.deleteById(id);
    }

    @Override
    public CriminalCase findCriminalCaseById(Long id) {
        Optional<CriminalCase> criminalCase = criminalCaseRepo.findById(id);
        if(criminalCase.isPresent()){
            return criminalCase.get();
        }else{
            throw new ResourceNotFoundException("Criminal Case", "Id", String.valueOf(id));
        }
    }

    @Override
    public List<CriminalCase> findCriminalCaseByStatus(CriminalCase.CaseStatus status, Pageable pageable) {
        return criminalCaseRepo.findAllByStatus(status, pageable);
    }

    @Override
    public boolean existsByNumber(String number) {
        return criminalCaseRepo.existsByNumber(number);
    }

    @Override
    public List <Evidence> findEvidenceByCriminalWithNumber(String number, Pageable pageable) {
        if(!criminalCaseRepo.existsByNumber(number)){
            throw new ResourceNotFoundException("Criminal Case", "Number", number);
        }
        return criminalCaseRepo.findEvidenceByCriminalWithNumber(number, pageable);
    }

    @Override
    public List<CriminalCase> findByDetectiveWithUsername(String username, Pageable pageable) {
        if(!detectiveService.existsByPerson_Username(username)){
            throw new ResourceNotFoundException("Detective", "Username", username);
        }
        return criminalCaseRepo.findByDetectiveWithUsername(username, pageable);
    }

}
