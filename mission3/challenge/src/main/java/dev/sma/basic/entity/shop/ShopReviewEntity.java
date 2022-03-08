package dev.sma.basic.entity.shop;

import dev.sma.basic.entity.BaseEntity;
import dev.sma.basic.entity.user.UserEntity;

import javax.persistence.*;


@Entity
@Table(name = "shop_review")
public class ShopReviewEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // N:1
    @ManyToOne(
            targetEntity = ShopEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    // N:1
    @ManyToOne(
            targetEntity = UserEntity.class,
            fetch = FetchType.LAZY
    )
    private UserEntity userEntity;

    public ShopReviewEntity() {
    }

    public ShopReviewEntity(Long id, ShopEntity shopEntity, UserEntity userEntity) {
        this.id = id;
        this.shopEntity = shopEntity;
        this.userEntity = userEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShopEntity getShopEntity() {
        return shopEntity;
    }

    public void setShopEntity(ShopEntity shopEntity) {
        this.shopEntity = shopEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
