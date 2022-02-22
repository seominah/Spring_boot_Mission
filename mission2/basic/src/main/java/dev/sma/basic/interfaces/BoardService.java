package dev.sma.basic.interfaces;

import dev.sma.basic.dto.BoardDto;

import java.util.List;

public interface BoardService {
    void createBoard(BoardDto boardDto);
    List<BoardDto> readBoardAll();
    BoardDto readBoard(int boardId);
    void updateBoard(int boardId, BoardDto boardDto);
    void deleteBoard(int boardId);
}
