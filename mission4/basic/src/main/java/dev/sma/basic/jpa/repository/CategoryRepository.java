package dev.sma.basic.jpa.repository;

import dev.sma.basic.jpa.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}
