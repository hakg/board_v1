package com.hakg.boardv1.api.controller;

import com.hakg.boardv1.api.dto.BoardRequest;
import com.hakg.boardv1.api.dto.BoardResponse;
import com.hakg.boardv1.domain.Board;
import com.hakg.boardv1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardResponse> getAllBoards() {
        return boardService.findAll().stream()
                .map(BoardResponse::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable Long id) {
        Board board = boardService.findById(id);
        return ResponseEntity.ok(BoardResponse.from(board));
    }

    @PostMapping
    public ResponseEntity<Void> createBoard(@RequestBody BoardRequest request) {
        boardService.save(request.toEntity());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBoard(@PathVariable Long id, @RequestBody BoardRequest request) {
        Board board = request.toEntity();
        board.setId(id);
        boardService.update(board);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.ok().build();
    }
}
