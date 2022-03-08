package dev.sma.basic.entity.shop;

import dev.sma.basic.entity.user.AreaEntity;
import dev.sma.basic.entity.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shop")
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contact;

    // 1:N
    @OneToMany(
            targetEntity = AreaEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "shopEntity"
    )
    private List<AreaEntity> areaEntityList = new ArrayList<>();

    // 1:N
    @OneToMany(
            targetEntity = UserEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "shopEntity"
    )
    private List<UserEntity> userEntityList = new ArrayList<>();

    // 1:N
    @OneToMany(
            targetEntity = ShopPostEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "shopEntity"
    )
    private List<ShopPostEntity> shopPostEntityList = new ArrayList<>();

    // 1:N
    @OneToMany(
            targetEntity = ShopReviewEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "shopEntity"
    )
    private List<ShopReviewEntity> shopReviewEntities = new ArrayList<>();

    public ShopEntity() {
    }

    public ShopEntity(Long id, String name, String contact, List<AreaEntity> areaEntityList, List<UserEntity> userEntityList, List<ShopPostEntity> shopPostEntityList, List<ShopReviewEntity> shopReviewEntities) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.areaEntityList = areaEntityList;
        this.userEntityList = userEntityList;
        this.shopPostEntityList = shopPostEntityList;
        this.shopReviewEntities = shopReviewEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<AreaEntity> getAreaEntityList() {
        return areaEntityList;
    }

    public void setAreaEntityList(List<AreaEntity> areaEntityList) {
        this.areaEntityList = areaEntityList;
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    public List<ShopPostEntity> getShopPostEntityList() {
        return shopPostEntityList;
    }

    public void setShopPostEntityList(List<ShopPostEntity> shopPostEntityList) {
        this.shopPostEntityList = shopPostEntityList;
    }

    public List<ShopReviewEntity> getShopReviewEntities() {
        return shopReviewEntities;
    }

    public void setShopReviewEntities(List<ShopReviewEntity> shopReviewEntities) {
        this.shopReviewEntities = shopReviewEntities;
    }
}
