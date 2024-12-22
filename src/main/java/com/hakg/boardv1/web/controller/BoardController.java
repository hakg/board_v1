package com.hakg.boardv1.web.controller;

import com.hakg.boardv1.domain.Board;
import com.hakg.boardv1.service.BoardService;
import com.hakg.boardv1.web.dto.BoardForm;
import com.hakg.boardv1.web.dto.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(@ModelAttribute PageRequest pageRequest, Model model) {
        Map<String, Object> result = boardService.findAllWithPaging(pageRequest);
        model.addAttribute("boards", result.get("boards"));
        model.addAttribute("totalCount", result.get("totalCount"));
        model.addAttribute("totalPages", result.get("totalPages"));
        model.addAttribute("currentPage", result.get("currentPage"));
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
        return "redirect:/board/list";
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
        return "redirect:/board/list";
    }
}
