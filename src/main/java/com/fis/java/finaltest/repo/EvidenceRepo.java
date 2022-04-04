package com.fis.java.finaltest.repo;

import com.fis.java.finaltest.entity.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceRepo extends JpaRepository<Evidence, Long> {
}
