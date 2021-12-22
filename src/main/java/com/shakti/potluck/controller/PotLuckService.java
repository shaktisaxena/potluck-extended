package com.shakti.potluck.controller;

import com.shakti.potluck.entity.PotLuck;
import com.shakti.potluck.entity.PotLuckDto;
import com.shakti.potluck.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PotLuckService {

    private final PotLuckDao _potLuckDao;
    private final ModelMapper mapper;

    public void createPotLuck(PotLuckDto potLuckDto) {
        checkIfPotLuckNameExists(potLuckDto.getName());
        PotLuck potLuck = mapper.map(potLuckDto, PotLuck.class);
        _potLuckDao.save(potLuck);
    }

    private void checkIfPotLuckNameExists(String name) {
        _potLuckDao.findByName(name)
                     .ifPresent(value ->
                         {
                             log.error("Potluck with name is already present. : {}", value);
                            throw new IllegalStateException("found");
                         });
    }

    public PotLuckDto findPotLuckByName(String potLuckName) {
        return mapper.map(_potLuckDao.findByName(potLuckName)
                .orElseThrow(ResourceNotFoundException::new), PotLuckDto.class);
    }

    public void updatePotLuck(PotLuckDto potLuckDto) {
        _potLuckDao.findByName(potLuckDto.getName()).orElseThrow(ResourceNotFoundException::new);
        _potLuckDao.update(mapper.map(potLuckDto, PotLuck.class));
    }
}
