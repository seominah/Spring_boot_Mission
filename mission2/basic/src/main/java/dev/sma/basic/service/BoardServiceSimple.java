package dev.sma.basic.service;

import dev.sma.basic.dto.BoardDto;
import dev.sma.basic.interfaces.BoardRepository;
import dev.sma.basic.interfaces.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceSimple implements BoardService {
    private static final Logger logger = LoggerFactory.getLogger(BoardServiceSimple.class);
    private final BoardRepository boardRepository;

    public BoardServiceSimple(
            @Autowired BoardRepository boardRepository
    ) {
        this.boardRepository = boardRepository;
    }


    @Override
    public void createBoard(BoardDto boardDto) {
        if (!this.boardRepository.save(boardDto)) {
            throw new RuntimeException("Save Failed");
        }
        logger.info("Save New Board");
    }

    @Override
    public List<BoardDto> readBoardAll() {
        if (this.boardRepository.finaAll().isEmpty()) {
            logger.info("I Can't Find Any Board");
        }
        return this.boardRepository.finaAll();
    }

    @Override
    public BoardDto readBoard(int boardId) {
        return this.boardRepository.findById(boardId);
    }

    @Override
    public void updateBoard(int boardId, BoardDto boardDto) {
        this.boardRepository.update(boardId, boardDto);
    }

    @Override
    public void deleteBoard(int boardId) {
        this.boardRepository.delete(boardId);
    }
}
