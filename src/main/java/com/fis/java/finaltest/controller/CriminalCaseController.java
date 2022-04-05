package com.fis.java.finaltest.controller;

import com.fis.java.finaltest.dto.CriminalCaseDto;
import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.service.CriminalCaseService;
import com.fis.java.finaltest.utils.CriminalCaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class CriminalCaseController {
    @Autowired
    CriminalCaseService criminalCaseService;

    @GetMapping("/criminal-cases")
    public ResponseEntity<List<CriminalCase>> getAllCriminalCase(@RequestParam("page")Optional<Integer> page,
                                                                 @RequestParam("size") Optional<Integer> size){
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(6));
        return ResponseEntity.ok(criminalCaseService.findCriminalCases(pageable));
    }

    @PostMapping("/criminal-case/save")
    public ResponseEntity<CriminalCase> saveCriminalCase(@Validated @RequestBody CriminalCaseDto criminalCaseDto){
        CriminalCase criminalCase = CriminalCaseUtils.convertFromDto(criminalCaseDto);
        criminalCase = criminalCaseService.saveCriminalCase(criminalCase);
        log.info("Criminal Case with id {} created", criminalCase.getId());
        return new ResponseEntity<CriminalCase>(criminalCase, HttpStatus.CREATED);
    }

    @PostMapping("/criminal-case/update/{id}")
    public ResponseEntity<CriminalCase> updateCriminalCase(@Validated @RequestBody CriminalCaseDto criminalCaseDto,
                                                           @PathVariable("id") Optional<Long> id){
        Long criminalCaseId = id.orElse(null);
        CriminalCase criminalCase = CriminalCaseUtils.convertFromDto(criminalCaseDto);
        criminalCase = criminalCaseService.updateCriminalCase(criminalCase, criminalCaseId);
        log.info("Criminal Case with id {} updated", criminalCase.getId());
        return new ResponseEntity<CriminalCase>(criminalCase,
                HttpStatus.OK);
    }

    @GetMapping("/criminal-case/delete/{id}")
    public ResponseEntity<String> deleteCriminalCase(@PathVariable("id") Optional<Long> id){
        Long criminalCaseId = id.orElse(null);
        criminalCaseService.deleteCriminalCase(criminalCaseId);
        log.info("Criminal Case with id {} deleted", id);
        return new ResponseEntity<String>("Criminal Case deleted successfully.",
                HttpStatus.OK);
    }

    @GetMapping("/criminal-case/{id}")
    public ResponseEntity<CriminalCase> findCriminalCaseById(@PathVariable("id") Optional<Long> id){
        Long criminalCaseId = id.orElse(null);
        return new ResponseEntity<CriminalCase>(criminalCaseService.findCriminalCaseById(criminalCaseId),
                HttpStatus.OK);
    }

    // endpoint to list of criminal cases with the specified case status
    @GetMapping("/criminal-case/status/{status}")
    public ResponseEntity<?> findCriminalCaseByStatus(@PathVariable("status") Optional<String> status,
                                                                       @RequestParam("page") Optional<Integer> page,
                                                                       @RequestParam("size") Optional<Integer> size){
        if(status.isPresent()){
            try {
                CriminalCase.CaseStatus caseStatus = CriminalCase.CaseStatus.valueOf(status.get());
                Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(6));
                return new ResponseEntity<>(criminalCaseService.findCriminalCaseByStatus(caseStatus, pageable), HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status is not Case Status.");
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status is required.");
        }
    }

    // list of evidences of the specified criminal number
    @GetMapping("/evidence-of-criminal/{number}")
    public ResponseEntity<?> findEvidenceByCriminalWithNumber(@PathVariable("number") String number,
                                                              @RequestParam("page") Optional<Integer> page,
                                                              @RequestParam("size") Optional<Integer> size){
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(6));
        return new ResponseEntity<>(criminalCaseService.findEvidenceByCriminalWithNumber(number, pageable),
                HttpStatus.OK);
    }

    //list of criminal cases of the specified username
    @GetMapping("/criminal-case-of-detctive/{username}")
    public ResponseEntity<?> findByDetectiveWithUsername(@PathVariable("username")String username,
                                                         @RequestParam("page") Optional<Integer> page,
                                                         @RequestParam("size") Optional<Integer> size){
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(6));
        return new ResponseEntity<>(criminalCaseService.findByDetectiveWithUsername(username, pageable),
                HttpStatus.OK);
    }
}
