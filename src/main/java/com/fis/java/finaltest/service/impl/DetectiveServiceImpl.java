package com.fis.java.finaltest.service.impl;

import com.fis.java.finaltest.entity.Detective;
import com.fis.java.finaltest.exception.ResourceNotFoundException;
import com.fis.java.finaltest.repo.DetectiveRepo;
import com.fis.java.finaltest.service.DetectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetectiveServiceImpl implements DetectiveService {
    @Autowired
    DetectiveRepo detectiveRepo;

    @Override
    public List<Detective> findDetectives(Pageable pageable) {
        return detectiveRepo.findAll(pageable).getContent();
    }

    @Override
    public Detective createDetective(Detective detective) {
        return detectiveRepo.save(detective);
    }

    @Override
    public Detective updateDetective(Detective detective, Long id) {
        if(!detectiveRepo.existsById(id)){
            throw new ResourceNotFoundException("Detective", "Id", String.valueOf(id));
        }
        detective.setId(id);
        return detectiveRepo.save(detective);
    }

    @Override
    public void deleteDetective(Long id) {
        if(!detectiveRepo.existsById(id)){
            throw new ResourceNotFoundException("Detective", "Id", String.valueOf(id));
        }
        detectiveRepo.deleteById(id);
    }

    @Override
    public Detective findDetectiveById(Long id) {
        Optional<Detective> detective = detectiveRepo.findById(id);
        if(detective.isPresent()){
            return detective.get();
        }else{
            throw new ResourceNotFoundException("Detective", "Id", String.valueOf(id));
        }
    }
}
