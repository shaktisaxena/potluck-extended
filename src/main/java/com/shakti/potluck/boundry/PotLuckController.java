package com.shakti.potluck.boundry;

import com.shakti.potluck.controller.PotLuckService;
import com.shakti.potluck.entity.PotLuckDetails;
import com.shakti.potluck.entity.PotLuckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(PotLuckController.ROOT)
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class PotLuckController {

    public static final String ROOT = "/api/v1/potluck/";
    private final PotLuckService _potLuckService;

    @PostMapping
    public ResponseEntity createPotLuck(@Valid @RequestBody PotLuckDto potLuckDto) {
        _potLuckService.createPotLuck(potLuckDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "{name}",produces = "application/json")
    public ResponseEntity<PotLuckDetails> getPotLuck(@PathVariable("name") String potLuckName) {
        return new ResponseEntity<>(_potLuckService.findPotLuckByName(potLuckName),HttpStatus.OK);
    }

    @GetMapping(value = "all",produces = "application/json")
    public ResponseEntity<PotLuckDetails> getAllPotLuck() {
        return new ResponseEntity(_potLuckService.findAllPotLuck(),HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity updatePotLuck(@Valid @RequestBody PotLuckDto potLuckDto) {
        _potLuckService.updatePotLuck(potLuckDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity deletePotLuck(@PathVariable("id") String id) {
        _potLuckService.deletePotLuck(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/_search/{name}")
    public ResponseEntity search(@PathVariable String name) {
        return new ResponseEntity(_potLuckService.existPotLuckByName(name),HttpStatus.OK);
    }
}
