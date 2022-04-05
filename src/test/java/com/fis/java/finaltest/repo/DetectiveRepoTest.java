package com.fis.java.finaltest.repo;

import com.fis.java.finaltest.entity.Detective;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DetectiveRepoTest {
    @Autowired
    private DetectiveRepo detectiveRepo;

    @Test
    void givenPersonUsername_whenExistsByPerson_Username_thenReturnTrue() {
        String username = "sa";

        boolean isExist = detectiveRepo.existsByPerson_Username(username);

        assertThat(isExist).isTrue();
    }

    @Test
    void givenPersonUsername_whenFindByPersonUsername_thenReturnDetective() {
        String username = "sa";

        Detective detective = detectiveRepo.findByPerson_Username(username);

        assertThat(detective.getId()).isEqualTo(19L);
    }
}