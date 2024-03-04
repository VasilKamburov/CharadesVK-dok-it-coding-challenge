package com.charadesvk.charadesdemo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Word extends HistorizedEntity {

    @Column
    String word;

    @Column
    String description;

    @Column
    int letterCount;

    public Word (String word, String description) {
        super();
        setWord(word);
        setDescription(description);
        setLetterCount(word.length());
    }
}
