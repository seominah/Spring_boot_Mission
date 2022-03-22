package dev.sma.basic.jpa.repository;

import dev.sma.basic.jpa.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
}
