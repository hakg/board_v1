package com.hakg.boardv1.web.dto;

import com.hakg.boardv1.domain.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm {
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

    public static BoardForm from(Board board) {
        BoardForm form = new BoardForm();
        form.setTitle(board.getTitle());
        form.setContent(board.getContent());
        form.setWriter(board.getWriter());
        return form;
    }
}
