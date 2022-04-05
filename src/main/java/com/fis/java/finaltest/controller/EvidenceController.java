package com.fis.java.finaltest.controller;

import com.fis.java.finaltest.dto.CriminalCaseDto;
import com.fis.java.finaltest.dto.EvidenceDto;
import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.entity.Evidence;
import com.fis.java.finaltest.service.CriminalCaseService;
import com.fis.java.finaltest.service.EvidenceService;
import com.fis.java.finaltest.utils.CriminalCaseUtils;
import com.fis.java.finaltest.utils.EvidenceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class EvidenceController {
    @Autowired
    EvidenceService evidenceService;

    @GetMapping("/evidences")
    public ResponseEntity<List<Evidence>> getAllEvidence(@RequestParam("page") Optional<Integer> page,
                                                             @RequestParam("size") Optional<Integer> size){
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(6));
        return ResponseEntity.ok(evidenceService.findEvidences(pageable));
    }

    @PostMapping("/evidence/save")
    public ResponseEntity<Evidence> saveEvidence(@Validated @RequestBody EvidenceDto evidenceDto){
        Evidence evidence = EvidenceUtils.convertFromDto(evidenceDto);
        evidence = evidenceService.createEvidence(evidence);
        log.info("Evidence with id {} created", evidence.getId());
        return new ResponseEntity<Evidence>(evidence, HttpStatus.OK);
    }

    @PostMapping("/evidence/update/{id}")
    public ResponseEntity<Evidence> updateEvidence(@Validated @RequestBody EvidenceDto evidenceDto,
                                                           @PathVariable("id") Optional<Long> id){
        Long evidenceId = id.orElse(null);
        Evidence evidence = EvidenceUtils.convertFromDto(evidenceDto);
        evidence = evidenceService.updateEvidence(evidence, evidenceId);
        log.info("Evidence with id {} updated", evidence.getId());
        return new ResponseEntity<Evidence>(evidence,
                HttpStatus.OK);
    }

    @GetMapping("/evidence/delete/{id}")
    public ResponseEntity<String> deleteEvidence(@PathVariable("id") Optional<Long> id){
        Long evidenceId = id.orElse(null);
        evidenceService.deleteEvidence(evidenceId);
        log.info("Evidence with id {} deleted", id);
        return new ResponseEntity<String>("Evidence deleted successfully.",
                HttpStatus.OK);
    }

    @GetMapping("/evidence/{id}")
    public ResponseEntity<Evidence> findEvidenceById(@PathVariable("id") Optional<Long> id){
        Long evidenceId = id.orElse(null);
        return new ResponseEntity<Evidence>(evidenceService.findEvidenceById(evidenceId),
                HttpStatus.OK);
    }

}
