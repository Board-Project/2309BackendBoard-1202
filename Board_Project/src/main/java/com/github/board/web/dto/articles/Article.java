package com.github.board.web.dto.articles;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Article {
    private int id;
    private String title;
    private String content;


}
