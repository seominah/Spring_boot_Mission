package dev.sma.basic.controller;

import dev.sma.basic.dto.BoardDto;
import dev.sma.basic.interfaces.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardRestController {
    private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
    private final BoardService boardService;


    public BoardRestController(
            @Autowired BoardService boardService
    ) {
        this.boardService = boardService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createBoard(@RequestBody BoardDto boardDto) {
        logger.info(boardDto.toString());
        this.boardService.createBoard(boardDto);
    }

    @GetMapping()
    public List<BoardDto> readBoardAll() {
        logger.info("in read all board");
        return this.boardService.readBoardAll();
    }

    @GetMapping("/{boardId}")
    public BoardDto readBoard(@PathVariable("boardId") int boardId) {
        logger.info("in read board");
        return this.boardService.readBoard(boardId);
    }

    @PutMapping("/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBoard(
            @PathVariable("boardId") int boardId,
            @RequestBody BoardDto boardDto
    ) {
        logger.info("update board name : " + boardDto);
        this.boardService.updateBoard(boardId, boardDto);
    }

    @DeleteMapping("/{boardId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBoard(@PathVariable("boardId") int boardId) {
        logger.info("delete board id : " + boardId);
        this.boardService.deleteBoard(boardId);
    }

}
