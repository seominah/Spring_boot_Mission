package dev.sma.basic.jpa.repository;

import dev.sma.basic.jpa.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BoardRepository extends CrudRepository<BoardEntity, Long> {
}
