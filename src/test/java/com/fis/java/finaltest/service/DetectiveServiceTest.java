package com.fis.java.finaltest.service;

import com.fis.java.finaltest.entity.CriminalCase;
import com.fis.java.finaltest.entity.Detective;
import com.fis.java.finaltest.repo.DetectiveRepo;
import com.fis.java.finaltest.service.impl.DetectiveServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DetectiveServiceTest {

    @Mock
    DetectiveRepo detectiveRepo;

    @InjectMocks
    @Autowired
    DetectiveServiceImpl detectiveService;

    Detective detective;

    List<Detective> detectiveList;

    @BeforeEach
    public void setUp(){
        detective = new Detective();
        detective.setId(19L);

        detectiveList = new ArrayList<>();
        detectiveList.add(detective);
    }

    @AfterEach
    public void tearDown(){
        detective = null;
        detectiveList = null;
    }
    @Test
    void findDetectives() {
        Page<Detective> page = new PageImpl<>(detectiveList);

        when(detectiveRepo.findAll(page.getPageable())).thenReturn(page);

        List<Detective> expected = detectiveService.findDetectives(page.getPageable());

        assertThat(expected.size()).isEqualTo(1);
    }

    @Test
    void  givenDetective_whenCreateDetective_thenReturnDetective() {
        when(detectiveRepo.save(any())).thenReturn(detective);

        Detective expected = detectiveService.createDetective(detective);

        assertThat(expected).isNotNull();
    }

    @Test
    void givenDetective_whenUpdateDetective_thenReturnDetective() {
        when(detectiveRepo.save(any())).thenReturn(detective);
        detective.setRanking(Detective.Rank.SENIOR);

        Detective expected = detectiveService.updateDetective(detective, detective.getId());

        assertThat(expected.getRanking()).isEqualTo(Detective.Rank.SENIOR);
    }

    @Test
    void givenDetectiveId_whenDeleteDetective_thenNothing() {
        Long id = 19L;
        willDoNothing().given(detectiveRepo).deleteById(id);

        detectiveRepo.deleteById(detective.getId());

        verify(detectiveRepo, times(1)).deleteById(id);
    }

    @Test
    void givenDetectiveId_whenFindDetectiveById_thenReturnDetective() {
        Long id = 19L;
        given(detectiveRepo.findById(id)).willReturn(Optional.of(detective));

        Detective expected = detectiveService.findDetectiveById(id);

        assertThat(expected).isNotNull();
    }
}