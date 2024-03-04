package com.charadesvk.charadesdemo.services.interfaces;

import com.charadesvk.charadesdemo.models.Word;

public interface IWordService {
    public Word create(String word, String description);
    public Word update(String word, String description);
}
