package dev.sma.basic.service;

import dev.sma.basic.model.BoardDto;
import dev.sma.basic.jpa.entity.BoardEntity;
import dev.sma.basic.jpa.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class JpaBoardService implements BoardService {
    private static final Logger logger = LoggerFactory.getLogger(JpaBoardService.class);
    private final BoardRepository boardRepository;

    public JpaBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;

    }

    @Override
    public BoardDto create(BoardDto dto) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(dto.getName());
        boardEntity = this.boardRepository.save(boardEntity);
        return new BoardDto(
                boardEntity.getId(),
                boardEntity.getName()
        );
    }

    @Override
    public BoardDto read(Long id) {
        Optional<BoardEntity> boardEntityOptional = this.boardRepository.findById(id);
        if (boardEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        BoardEntity boardEntity = boardEntityOptional.get();
        return new BoardDto(
                boardEntity.getId(),
                boardEntity.getName());
    }

    @Override
    public Collection<BoardDto> readAll() {
        List<BoardDto> boardDtoList = new ArrayList<>();
        this.boardRepository.findAll().forEach(boardEntity -> boardDtoList.add(
                new BoardDto(
                        boardEntity.getId(),
                        boardEntity.getName()
                )
        ));
        return boardDtoList;
    }

    @Override
    public boolean update(Long id, BoardDto dto) {
        Optional<BoardEntity> userEntityOptional = this.boardRepository.findById(id);
        if (userEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        BoardEntity boardEntity = userEntityOptional.get();
        boardEntity.setName(
                dto.getName() == null ? boardEntity.getName() : dto.getName()
        );
        this.boardRepository.save(boardEntity);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Optional<BoardEntity> boardEntityOptional = this.boardRepository.findById(id);
        if (boardEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        BoardEntity boardEntity = boardEntityOptional.get();
        this.boardRepository.delete(boardEntity);
        return true;
    }
}
