package dev.sma.basic.repository;

import dev.sma.basic.dto.BoardDto;
import dev.sma.basic.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BoardRepository extends CrudRepository<BoardEntity, Long> {
    Optional<BoardEntity> findByName(String name);
}
