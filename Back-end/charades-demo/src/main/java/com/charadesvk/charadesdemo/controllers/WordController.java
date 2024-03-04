package com.charadesvk.charadesdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
}
