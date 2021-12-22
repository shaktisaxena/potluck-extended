package com.shakti.potluck.boundry;

import com.shakti.potluck.controller.PotLuckService;
import com.shakti.potluck.entity.PotLuckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class PotLuckController {

    private final PotLuckService _potLuckService;

    @PostMapping("/potluck")
    public ResponseEntity createPotLuck(@Valid @RequestBody PotLuckDto potLuckDto) {
        _potLuckService.createPotLuck(potLuckDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/potluck/{name}")
    public ResponseEntity<PotLuckDto> getPotLuck(@PathVariable("name") String potLuckName) {
        return new ResponseEntity<>(_potLuckService.findPotLuckByName(potLuckName),HttpStatus.FOUND);
    }

    @PutMapping("/potluck")
    public ResponseEntity updatePotLuck(@Valid @RequestBody PotLuckDto potLuckDto) {
        _potLuckService.updatePotLuck(potLuckDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
