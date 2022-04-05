package com.fis.java.finaltest.repo;

import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.entity.Evidence;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CriminalCaseRepoTest {
    @Autowired
    CriminalCaseRepo criminalCaseRepo;

    @Test
    void givenCriminalCaseStatus_whenFindAllByStatus_thenReturnCriminalCaseList() {
        CriminalCase.CaseStatus status = CriminalCase.CaseStatus.SUBMITTED;
        Pageable pageable = PageRequest.of(0, 6);

        List<CriminalCase> list = criminalCaseRepo.findAllByStatus(status, pageable);

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void givenCriminalCaseNumber_whenExistByNumber_thenReturnTrue() {
        String number = "Number1";

        boolean isExist = criminalCaseRepo.existsByNumber(number);

        assertThat(isExist).isTrue();
    }

    @Test
    void givenCriminalCaseNumber_whenFindEvidenceByCriminalWithNumber_thenReturnEvidenceList() {
        String number = "Number1";
        Pageable pageable = PageRequest.of(0, 6);

        List<Evidence> list = criminalCaseRepo.findEvidenceByCriminalWithNumber(number, pageable);

        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void givenDetectiveWithUsername_whenFindByDetectiveWithUsername_thenReturnCriminalCaseList() {
        String username = "sa";
        Pageable pageable = PageRequest.of(0, 6);

        List<CriminalCase> list = criminalCaseRepo.findByDetectiveWithUsername(username, pageable);

        assertThat(list.size()).isEqualTo(1);
    }
}