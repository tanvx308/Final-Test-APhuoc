package com.fis.java.finaltest.repo;

import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.entity.Evidence;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CriminalCaseRepo extends JpaRepository<CriminalCase, Long> {
    List<CriminalCase> findAllByStatus(CriminalCase.CaseStatus status, Pageable pageable);

    boolean existsByNumber(String number);

    // list of evidences of the specified criminal number
    @Query("SELECT c.evidenceSet FROM CriminalCase c where c.number = ?1")
    List<Evidence> findEvidenceByCriminalWithNumber(String number, Pageable pageable);

    @Query("SELECT c FROM CriminalCase c inner join c.assigned a where a.person.username = :username or c.leadInvestigator.person.username = :username")
    List<CriminalCase> findByDetectiveWithUsername(@Param("username") String username, Pageable pageable);
}
