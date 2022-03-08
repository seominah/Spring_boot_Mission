package dev.sma.basic.repository;

import dev.sma.basic.dto.PostDto;
import dev.sma.basic.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
}
