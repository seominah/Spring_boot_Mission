package dev.sma.basic.jpa.repository;

import dev.sma.basic.jpa.entity.ShopPostEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShopPostRepository extends CrudRepository<ShopPostEntity, Long> {
}
