package com.hakg.boardv1.api.dto;

import com.hakg.boardv1.domain.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequest {
    private String title;
    private String content;
    private String writer;

    public Board toEntity() {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setWriter(writer);
        return board;
    }
}
