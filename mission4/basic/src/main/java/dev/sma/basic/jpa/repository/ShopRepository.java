package dev.sma.basic.jpa.repository;

import dev.sma.basic.jpa.entity.ShopEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepository extends CrudRepository<ShopEntity, Long> {
}
