package dev.sma.basic.controller;

import dev.sma.basic.model.BoardDto;
import dev.sma.basic.service.JpaBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final JpaBoardService boardService;

    public BoardController(@Autowired JpaBoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto dto) {
        return ResponseEntity.ok(this.boardService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> readBoard(@PathVariable("id") Long id) {
        BoardDto dto = boardService.read(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public ResponseEntity<Collection<BoardDto>> readAllBoard() {
        return ResponseEntity.ok(this.boardService.readAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBoard(
            @PathVariable("id") Long id,
            @RequestBody BoardDto dto) {
        if (!this.boardService.update(id, dto)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id) {
        if (!this.boardService.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
