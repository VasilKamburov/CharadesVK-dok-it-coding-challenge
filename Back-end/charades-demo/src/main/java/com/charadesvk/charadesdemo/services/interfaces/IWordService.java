package com.charadesvk.charadesdemo.services.interfaces;

import java.util.List;
import java.util.UUID;
import com.charadesvk.charadesdemo.models.Word;

public interface IWordService {
    public List<Word> getAll();
    public Word getByWord(String word);
    public Word getRandom();
    public Word create(String word, String description);
    public Word update(String word, String description);
    public void delete(UUID id);
}
