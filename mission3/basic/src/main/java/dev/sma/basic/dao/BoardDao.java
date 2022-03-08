package dev.sma.basic.dao;

import dev.sma.basic.dto.BoardDto;
import dev.sma.basic.entity.BoardEntity;
import dev.sma.basic.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BoardDao {
    private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);
    private final BoardRepository boardRepository;

    public BoardDao(@Autowired BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void createBoard(BoardDto boardDto) {
        Optional<BoardEntity> targetEntity = this.boardRepository.findByName(boardDto.getName());
        if (targetEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(boardDto.getName());
        this.boardRepository.save(boardEntity);
    }

    public BoardEntity readBoard(int id) {
        Optional<BoardEntity> targetEntity = this.boardRepository.findById((long) id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return targetEntity.get();
    }

    public Iterator<BoardEntity> readAllBoard() {
        return this.boardRepository.findAll().iterator();
    }

    public void updateBoard(int id, BoardDto boardDto) {
        Optional<BoardEntity> targetEntity = this.boardRepository.findById((long) id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        BoardEntity boardEntity = targetEntity.get();
        if (Objects.equals(boardEntity.getName(), boardDto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        boardEntity.setName(boardDto.getName());
        this.boardRepository.save(boardEntity);
    }

    public void deleteBoard(int id) {
        Optional<BoardEntity> targetEntity = this.boardRepository.findById((long) id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.boardRepository.delete(targetEntity.get());
    }
}
