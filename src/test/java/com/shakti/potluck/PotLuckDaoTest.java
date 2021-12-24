package com.shakti.potluck;

import com.shakti.potluck.controller.PotLuckDao;
import com.shakti.potluck.controller.PotLuckMongoRepository;
import com.shakti.potluck.entity.PotLuck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class PotLuckDaoTest {

    @MockBean
    PotLuckMongoRepository mongoRepository;

    @Autowired
    PotLuckDao _sut;

    @Test
    public void createPotLuck()
    {
        //arrange
        PotLuck potLuck = new PotLuck();
        potLuck.setFamilyCount(2);
        LocalDate date1 = LocalDate.of(2014, 9, 11);
        potLuck.setPlannedDate(date1);
        potLuck.setName("test");
        potLuck.setFoodItems(new String[]{"chole"});
        when(mongoRepository.save(potLuck)).thenReturn(potLuck);

        //act
        _sut.save(potLuck);
        //assert
        verify(mongoRepository,times(1)).save(potLuck);

    }
}
