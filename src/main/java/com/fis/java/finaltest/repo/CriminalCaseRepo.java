package com.fis.java.finaltest.repo;

import com.fis.java.finaltest.entity.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalCaseRepo extends JpaRepository<CriminalCase, Long> {
}
