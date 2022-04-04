package com.fis.java.finaltest.repo;

import com.fis.java.finaltest.entity.Detective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectiveRepo extends JpaRepository<Detective, Long> {
}
