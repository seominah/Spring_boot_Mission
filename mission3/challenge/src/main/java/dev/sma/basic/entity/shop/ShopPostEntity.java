package dev.sma.basic.entity.shop;

import dev.sma.basic.entity.BaseEntity;
import dev.sma.basic.entity.user.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;

@Entity
@Table(name = "shop_post")
public class ShopPostEntity extends BaseEntity {
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
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public ShopPostEntity() {
    }

    public ShopPostEntity(Long id, ShopEntity shopEntity, UserEntity userEntity) {
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
        if (userEntity.getRoleType() != UserEntity.RoleType.OWNER) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        this.userEntity = userEntity;
    }
}
