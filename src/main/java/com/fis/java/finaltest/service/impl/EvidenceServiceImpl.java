package com.fis.java.finaltest.service.impl;

import com.fis.java.finaltest.entity.Evidence;
import com.fis.java.finaltest.exception.ResourceNotFoundException;
import com.fis.java.finaltest.repo.EvidenceRepo;
import com.fis.java.finaltest.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvidenceServiceImpl implements EvidenceService {
    @Autowired
    EvidenceRepo evidenceRepo;

    @Override
    public List<Evidence> findEvidences(Pageable pageable) {
        return evidenceRepo.findAll(pageable).getContent();
    }

    @Override
    public Evidence createEvidence(Evidence evidence) {
        return evidenceRepo.save(evidence);
    }

    @Override
    public Evidence updateEvidence(Evidence evidence, Long id) {
        if(!evidenceRepo.existsById(id)){
            throw new ResourceNotFoundException("Evidence", "Id", String.valueOf(id));
        }
        evidence.setId(id);
        return evidenceRepo.save(evidence);
    }

    @Override
    public void deleteEvidence(Long id) {
        if(!evidenceRepo.existsById(id)){
            throw new ResourceNotFoundException("Evidence", "Id", String.valueOf(id));
        }
        evidenceRepo.deleteById(id);
    }

    @Override
    public Evidence findEvidenceById(Long id) {
        Optional<Evidence> evidence = evidenceRepo.findById(id);
        if(evidence.isPresent()){
            return evidence.get();
        }else{
            throw new ResourceNotFoundException("Evidence", "Id", String.valueOf(id));
        }
    }
}
