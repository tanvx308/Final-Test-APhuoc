package com.fis.java.finaltest.repo;

import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.entity.Detective;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetectiveRepo extends JpaRepository<Detective, Long> {
    boolean existsByPerson_Username(String username);

    Detective findByPerson_Username(String username);
}
