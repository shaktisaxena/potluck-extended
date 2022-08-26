package com.shakti.potluck.controller;

import com.shakti.potluck.entity.PotLuck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class PotLuckDaoTest {

    @MockBean
    PotLuckMongoRepository _repository;

    PotLuckDao _sut;

    @BeforeEach
    void setUp()
    {
        _sut = new PotLuckDao(_repository);
    }

    @Test
    void save() {

        PotLuck newPotluck= new PotLuck();
        newPotluck.setName("testchanged");
        newPotluck.setFamilyCount(2);
        newPotluck.setPlannedDate( LocalDate.of(2021,12,10));
        newPotluck.setFoodItems(new String[]{"chole"});

        when(_repository.save(newPotluck)).thenReturn(newPotluck);

        //act
        PotLuck _actual=_sut.save(newPotluck);

        //assert
        verify(_repository,times(1)).save(newPotluck);
        assertEquals(newPotluck,_actual);
    }

    @Test
    void update() {

        PotLuck newPotluck= new PotLuck();
        newPotluck.setName("testchanged");
        newPotluck.setFamilyCount(2);
        newPotluck.setPlannedDate( LocalDate.of(2021,12,10));
        newPotluck.setFoodItems(new String[]{"chole"});

        when(_repository.save(newPotluck)).thenReturn(newPotluck);

        //act
          PotLuck _actual=_sut.update(newPotluck);

        //assert
        verify(_repository,times(1)).save(newPotluck);
        assertEquals(newPotluck,_actual);
    }

    @Test
    void delete() {

        PotLuck potLuck= new PotLuck();
        potLuck.setName("testchanged");
        potLuck.setFamilyCount(2);
        potLuck.setPlannedDate( LocalDate.of(2021,12,10));
        potLuck.setFoodItems(new String[]{"chole"});

        //act
        _sut.delete(potLuck);

        //assert
        verify(_repository,times(1)).delete(potLuck);
    }

    @Test
    void findByName() {
        ArgumentCaptor<String> argumentCaptor= ArgumentCaptor.forClass(String.class);
        PotLuck potLuck= new PotLuck();
        potLuck.setName("test");
        potLuck.setFamilyCount(2);
        potLuck.setPlannedDate( LocalDate.of(2021,12,10));
        potLuck.setFoodItems(new String[]{"chole"});

        when(_repository.findByName("test")).thenReturn(Optional.of(potLuck));

        //act
        Optional<PotLuck> _actual=_sut.findByName("test");

        //assert
        verify(_repository).findByName(argumentCaptor.capture());
        String value = argumentCaptor.getValue();
        assertEquals(value,"test");
        assertEquals(_actual.get(),potLuck);
    }

    @Test
    void findById() {

        ArgumentCaptor<String> argumentCaptor= ArgumentCaptor.forClass(String.class);
        PotLuck potLuck= new PotLuck();
        potLuck.setName("test");
        potLuck.setFamilyCount(2);
        potLuck.setPlannedDate( LocalDate.of(2021,12,10));
        potLuck.setFoodItems(new String[]{"chole"});

        when(_repository.findById("1000")).thenReturn(Optional.of(potLuck));

        //act
        Optional<PotLuck> _actual=_sut.findById("1000");

        //assert
        verify(_repository).findById(argumentCaptor.capture());
        String value = argumentCaptor.getValue();
        assertEquals(value,"1000");
        assertEquals(_actual.get(),potLuck);
    }
}