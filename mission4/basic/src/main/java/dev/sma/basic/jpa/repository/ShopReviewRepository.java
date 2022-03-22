package dev.sma.basic.jpa.repository;

import dev.sma.basic.jpa.entity.ShopReviewEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShopReviewRepository extends CrudRepository<ShopReviewEntity, Long> {
}
