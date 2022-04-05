package com.fis.java.finaltest.service;

import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.entity.Evidence;
import com.fis.java.finaltest.repo.CriminalCaseRepo;
import com.fis.java.finaltest.service.impl.CriminalCaseServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriminalCaseServiceTest {
    @Mock
    private CriminalCaseRepo criminalCaseRepo;

    @InjectMocks
    @Autowired
    private CriminalCaseServiceImpl criminalCaseService;

    private CriminalCase criminalCase;

    private List<CriminalCase> criminalCaseList;

    @BeforeEach
    public void setUp(){
        criminalCase = new CriminalCase();
        criminalCase.setId(9L);
        criminalCase.setNumber("Number1");
        criminalCase.setStatus(CriminalCase.CaseStatus.SUBMITTED);

        criminalCaseList = new ArrayList<>();
        criminalCaseList.add(criminalCase);
    }

    @AfterEach
    public void tearDown(){
        criminalCase = null;
        criminalCaseList = null;
    }

    @Test
    void givenCriminalCaseList_whenFindCriminalCases_thenReturnCriminalCaseList() {
        Page<CriminalCase> page = new PageImpl<>(criminalCaseList);

        when(criminalCaseRepo.findAll(page.getPageable())).thenReturn(page);

        List<CriminalCase> list = criminalCaseService.findCriminalCases(page.getPageable());

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void  givenCriminalCase_whenSaveCriminalCase_thenReturnCriminalCase() {
        when(criminalCaseRepo.save(any())).thenReturn(criminalCase);

       CriminalCase expected = criminalCaseService.saveCriminalCase(criminalCase);

        assertThat(expected).isNotNull();
    }

    @Test
    void givenCriminalCase_whenUpdateCriminalCase_thenReturnCriminalCase() {
        when(criminalCaseRepo.save(any())).thenReturn(criminalCase);
        criminalCase.setStatus(CriminalCase.CaseStatus.CLOSED);

        CriminalCase expected = criminalCaseService.updateCriminalCase(criminalCase, criminalCase.getId());

        assertThat(expected.getStatus()).isEqualTo(CriminalCase.CaseStatus.CLOSED);
    }

    @Test
    void givenCriminalCaseId_whenDeleteCriminalCase_thenNothing() {
        Long id = 9L;
        willDoNothing().given(criminalCaseRepo).deleteById(id);

        criminalCaseRepo.deleteById(criminalCase.getId());

        verify(criminalCaseRepo, times(1)).deleteById(id);
    }

    @Test
    void  givenCriminalCaseId_whenFindCriminalCaseById_thenReturnCriminalCase() {
        Long id = 9L;
        given(criminalCaseRepo.findById(id)).willReturn(Optional.of(criminalCase));

        CriminalCase expected = criminalCaseService.findCriminalCaseById(id);

        assertThat(expected).isNotNull();
    }

    @Test
    void givenCriminalCaseStatus_whenFindCriminalCaseByStatus_thenReturnCriminalCase() {
        CriminalCase.CaseStatus status = CriminalCase.CaseStatus.SUBMITTED;
        Page<CriminalCase> page = new PageImpl<>(criminalCaseList);

        given(criminalCaseRepo.findAllByStatus(status, page.getPageable())).willReturn(criminalCaseList);

        List<CriminalCase> list = criminalCaseService.findCriminalCaseByStatus(status, page.getPageable());

        assertThat(list.size()).isEqualTo(criminalCaseList.size());
    }

    @Test
    void givenCriminalCaseNumber_whenExistsByNumber_thenReturnTrue() {
        String number = "Number1";
        given(criminalCaseRepo.existsByNumber(number)).willReturn(true);

        boolean expected = criminalCaseService.existsByNumber(number);

        assertThat(expected).isTrue();
    }

    @Test
    void givenCriminalCaseNumber_whenFindEvidenceByCriminalWithNumber_thenReturnEvidenceList() {
        String number = "Number1";
        List<Evidence> evidenceList = Arrays.asList(new Evidence());
        Page<CriminalCase> page = new PageImpl<>(criminalCaseList);

        given(criminalCaseRepo.findEvidenceByCriminalWithNumber(number, page.getPageable())).willReturn(evidenceList);

        List<Evidence> expected = criminalCaseService.findEvidenceByCriminalWithNumber(number, page.getPageable());

        assertThat(expected.size()).isEqualTo(evidenceList.size());
    }
}