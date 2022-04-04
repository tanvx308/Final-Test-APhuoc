package com.fis.java.finaltest.controller;

import com.fis.java.finaltest.dto.DetectiveDto;
import com.fis.java.finaltest.entity.Detective;
import com.fis.java.finaltest.service.DetectiveService;
import com.fis.java.finaltest.utils.DetectiveUtils;
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
@Slf4j
@RequestMapping("/api")
public class DetectiveController {
    @Autowired
    DetectiveService detectiveService;

    @GetMapping("/detectives")
    public ResponseEntity<List<Detective>> getAllDetective(@RequestParam("page") Optional<Integer> page,
                                                          @RequestParam("size") Optional<Integer> size){
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(6));
        return ResponseEntity.ok(detectiveService.findDetectives(pageable));
    }

    @PostMapping("/detective/save")
    public ResponseEntity<Detective> saveDetective(@Validated @RequestBody DetectiveDto detectiveDto){
        Detective detective = DetectiveUtils.convertFromDto(detectiveDto);
        detective = detectiveService.createDetective(detective);
        log.info("Detective with id {} created", detective.getId());
        return new ResponseEntity<Detective>(detective, HttpStatus.OK);
    }

    @PostMapping("/detective/update/{id}")
    public ResponseEntity<Detective> updateDetective(@Validated @RequestBody DetectiveDto detectiveDto,
                                                   @PathVariable("id") Optional<Long> id){
        Long detectiveId = id.orElse(null);
        Detective detective = DetectiveUtils.convertFromDto(detectiveDto);
        detective = detectiveService.updateDetective(detective, detectiveId);
        log.info("Detective with id {} updated", detective.getId());
        return new ResponseEntity<Detective>(detective,
                HttpStatus.OK);
    }

    @GetMapping("/detective/delete/{id}")
    public ResponseEntity<String> deleteDetective(@PathVariable("id") Optional<Long> id){
        Long detectiveId = id.orElse(null);
        detectiveService.deleteDetective(detectiveId);
        log.info("Detective with id {} deleted", id);
        return new ResponseEntity<String>("Detective deleted successfully.",
                HttpStatus.OK);
    }

    @GetMapping("/detective/{id}")
    public ResponseEntity<Detective> findDetectiveById(@PathVariable("id") Optional<Long> id){
        Long detectiveId = id.orElse(null);
        return new ResponseEntity<Detective>(detectiveService.findDetectiveById(detectiveId),
                HttpStatus.OK);
    }
}
