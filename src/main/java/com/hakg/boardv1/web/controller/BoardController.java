package com.hakg.boardv1.web.controller;

import com.hakg.boardv1.domain.Board;
import com.hakg.boardv1.service.BoardService;
import com.hakg.boardv1.web.dto.BoardForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "board/list";
    }

    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("board", new BoardForm());
        return "board/write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute BoardForm form) {
        boardService.save(form.toEntity());
        return "redirect:/board";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", BoardForm.from(board));
        return "board/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute BoardForm form) {
        Board board = form.toEntity();
        board.setId(id);
        boardService.update(board);
        return "redirect:/board/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board";
    }
}
