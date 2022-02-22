package dev.sma.basic.repository;

import dev.sma.basic.dto.BoardDto;
import dev.sma.basic.interfaces.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardRepositoryInMemory implements BoardRepository {
    private static final Logger logger = LoggerFactory.getLogger(BoardRepositoryInMemory.class);
    private final List<BoardDto> boardList;

    public BoardRepositoryInMemory() {
        this.boardList = new ArrayList<>();
    }

    @Override
    public boolean save(BoardDto boardDto) {
        boardDto.setBoardId(boardList.size() + 1);
        return this.boardList.add(boardDto);
    }

    @Override
    public List<BoardDto> finaAll() {
        return this.boardList;
    }

    @Override
    public BoardDto findById(int boardId) {
        return this.boardList.get(boardId - 1);
    }

    @Override
    public boolean update(int boardId, BoardDto boardDto) {
        BoardDto targetBoard = this.boardList.get(boardId - 1);
        if (boardDto.getName() != null) {
            targetBoard.setName(boardDto.getName());
        }
        this.boardList.set(boardId - 1, targetBoard);
        return true;
    }

    @Override
    public boolean delete(int boardId) {
        this.boardList.remove(boardId - 1);
        return true;
    }
}
