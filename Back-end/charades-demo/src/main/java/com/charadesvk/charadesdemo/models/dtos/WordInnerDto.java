package com.charadesvk.charadesdemo.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class WordInnerDto {
    @NotEmpty(message = "Word must not be empty")
    private String word;
    
    @NotEmpty(message = "Description must not be empty")
    private String description;
}
