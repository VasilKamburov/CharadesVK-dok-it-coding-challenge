package com.charadesvk.charadesdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import com.charadesvk.charadesdemo.models.Word;
import com.charadesvk.charadesdemo.models.dtos.WordInnerDto;
import com.charadesvk.charadesdemo.services.interfaces.IWordService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/words")
public class WordController {
    @Autowired
    private IWordService service;

    @GetMapping
    public ResponseEntity<List<Word>> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Word> 
    searchByWord (
        @RequestParam(name = "word") String word
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getByWord(word));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/random")
    public ResponseEntity<Word> getRandom() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getRandom());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Word> addWord(
        @Valid @RequestBody() WordInnerDto dto
    ) {
        try {
            Word word = service.create(dto.getWord(), dto.getDescription());
            return ResponseEntity.status(HttpStatus.CREATED).body(word);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    } 

    @PutMapping("/modify-description")
    public ResponseEntity<Word> modifyDescription(
        @Valid @RequestBody() WordInnerDto dto
    ) {
        try {
            Word word = service.update(dto.getWord(), dto.getDescription());
            return ResponseEntity.ok().body(word);        
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
        @PathVariable UUID id
    ) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Word deleted successfully");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
