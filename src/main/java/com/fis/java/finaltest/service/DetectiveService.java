package com.fis.java.finaltest.service;

import com.fis.java.finaltest.entity.Detective;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DetectiveService {
    List<Detective> findDetectives(Pageable pageable);

    Detective createDetective(Detective detective);

    Detective updateDetective(Detective detective, Long id);

    void deleteDetective(Long id);

    Detective findDetectiveById(Long id);
}
