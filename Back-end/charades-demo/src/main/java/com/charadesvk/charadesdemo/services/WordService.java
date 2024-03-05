package com.charadesvk.charadesdemo.services;

import java.util.List;
import java.util.UUID;
import java.time.*;
import java.util.Random;

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

    @Override
    public List<Word> getAll() {
        List<Word> words = repository.findAllByOrderByWordAsc();
        if (words.size() == 0) {
            throw new EntityNotFoundException("There are currently no words added");
        }
        return words;
    }

    @Override
    public Word getByWord(String word) {
        Word foundWord = repository.findByWord(word);
        if (foundWord == null) {
            throw new EntityNotFoundException("There is no such word");
        }
        return foundWord;
    }

    @Override
    public Word getRandom() {
        List<Word> words = repository.findAll();
        if (words.size() == 0) {
            throw new EntityNotFoundException("No words in here :)");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }

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

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
