package com.maids.libraryTask.controller;


import com.maids.libraryTask.dto.PatronDTO;
import com.maids.libraryTask.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;



    @PostMapping
    public ResponseEntity<PatronDTO> registerPatron(@Validated @RequestBody PatronDTO patronDto) {
        PatronDTO savedPatron = patronService.savePatron(patronDto);
        return new ResponseEntity<>(savedPatron, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatronById(@PathVariable Long id) {
        PatronDTO patronDto = patronService.getPatronById(id);
        return ResponseEntity.ok(patronDto);
    }

    @GetMapping
    public ResponseEntity<List<PatronDTO>> getAllPatrons() {
        List<PatronDTO> patrons = patronService.getAllPatrons();
        return ResponseEntity.ok(patrons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable Long id, @Validated @RequestBody PatronDTO patronDto) {
        PatronDTO updatedPatron = patronService.updatePatron(id, patronDto);
        return ResponseEntity.ok(updatedPatron);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

