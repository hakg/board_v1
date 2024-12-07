package com.hakg.boardv1.service;

import com.hakg.boardv1.domain.Board;
import com.hakg.boardv1.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public List<Board> findAll() {
        return boardMapper.findAll();
    }

    public Board findById(Long id) {
        boardMapper.incrementViewCount(id);
        return boardMapper.findById(id);
    }

    @Transactional
    public void save(Board board) {
        boardMapper.save(board);
    }

    @Transactional
    public void update(Board board) {
        boardMapper.update(board);
    }

    @Transactional
    public void delete(Long id) {
        boardMapper.delete(id);
    }
}
