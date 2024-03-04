package com.charadesvk.charadesdemo.services;

import java.util.UUID;
import java.time.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charadesvk.charadesdemo.models.Word;
import com.charadesvk.charadesdemo.repositories.IWordRepository;
import com.charadesvk.charadesdemo.services.interfaces.IWordService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class WordService implements IWordService {
    @Autowired
    private IWordRepository repository;

    //handle duplicate and errors for both
    @Override
    public Word create(String word, String description) {
        if (repository.findByWord(word) != null) {
            throw new EntityExistsException("This word has already been added");
        }
        Word newWord = new Word(word, description);
        return repository.save(newWord);
    }

    @Override
    public Word update(String word, String description) {
        Word updatedWord = repository.findByWord(word);
        if (updatedWord == null) {
            throw new EntityNotFoundException("There is no such word");
        }
        updatedWord.setDescription(description);
        updatedWord.setLastUpdatedOn(LocalDate.now());
        return repository.save(updatedWord);
    }

}
