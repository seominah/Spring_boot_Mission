package dev.sma.basic.interfaces;

import dev.sma.basic.dto.BoardDto;

import java.util.List;

public interface BoardRepository {
    boolean save(BoardDto boardDto);
    List<BoardDto> finaAll();
    BoardDto findById(int boardId);
    boolean update(int boardId, BoardDto boardDto);
    boolean delete(int boardId);
}
