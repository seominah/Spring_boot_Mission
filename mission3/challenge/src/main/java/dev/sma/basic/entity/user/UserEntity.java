package dev.sma.basic.entity.user;

import dev.sma.basic.entity.BaseEntity;
import dev.sma.basic.entity.board.PostEntity;
import dev.sma.basic.entity.shop.ShopEntity;
import dev.sma.basic.entity.shop.ShopPostEntity;
import dev.sma.basic.entity.shop.ShopReviewEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @OneToMany(
            targetEntity = AreaEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "userEntity"
    )
    private List<AreaEntity> areaEntityList = new ArrayList<>();

    @OneToMany(
            targetEntity = PostEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "userEntity"
    )
    private List<PostEntity> postEntityList = new ArrayList<>();

    @ManyToOne(
            targetEntity = ShopEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    @OneToMany(
            targetEntity = ShopPostEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "userEntity"
    )
    private List<ShopPostEntity> shopPostEntityList = new ArrayList<>();

    @OneToMany(
            targetEntity = ShopReviewEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "userEntity"
    )
    private List<ShopReviewEntity> shopReviewEntityList = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(Long id, String name, String email, RoleType roleType, List<AreaEntity> areaEntityList, List<PostEntity> postEntityList, ShopEntity shopEntity, List<ShopPostEntity> shopPostEntityList, List<ShopReviewEntity> shopReviewEntityList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roleType = roleType;
        this.areaEntityList = areaEntityList;
        this.postEntityList = postEntityList;
        this.shopEntity = shopEntity;
        this.shopPostEntityList = shopPostEntityList;
        this.shopReviewEntityList = shopReviewEntityList;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public List<AreaEntity> getAreaEntityList() {
        return areaEntityList;
    }

    public void setAreaEntityList(List<AreaEntity> areaEntityList) {
        this.areaEntityList = areaEntityList;
    }

    public List<PostEntity> getPostEntityList() {
        return postEntityList;
    }

    public void setPostEntityList(List<PostEntity> postEntityList) {
        this.postEntityList = postEntityList;
    }

    public ShopEntity getShopEntity() {
        return shopEntity;
    }

    public void setShopEntity(ShopEntity shopEntity) {
        this.shopEntity = shopEntity;
    }

    public List<ShopPostEntity> getShopPostEntityList() {
        return shopPostEntityList;
    }

    public void setShopPostEntityList(List<ShopPostEntity> shopPostEntityList) {
        this.shopPostEntityList = shopPostEntityList;
    }

    public List<ShopReviewEntity> getShopReviewEntityList() {
        return shopReviewEntityList;
    }

    public void setShopReviewEntityList(List<ShopReviewEntity> shopReviewEntityList) {
        this.shopReviewEntityList = shopReviewEntityList;
    }

    public enum RoleType {
        USER, OWNER
    }
}
