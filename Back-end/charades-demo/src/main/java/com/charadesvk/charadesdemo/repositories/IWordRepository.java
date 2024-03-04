package com.charadesvk.charadesdemo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charadesvk.charadesdemo.models.Word;

@Repository
public interface IWordRepository extends JpaRepository<Word, UUID> {
    public Word findByWord(String word);
}
