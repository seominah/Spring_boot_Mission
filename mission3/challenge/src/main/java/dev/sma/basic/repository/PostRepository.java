package dev.sma.basic.repository;

import dev.sma.basic.entity.board.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
}
