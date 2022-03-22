package dev.sma.basic.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "shop")
public class ShopEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(
            targetEntity = UserEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @ManyToOne(
            targetEntity = AreaEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "loated_at")
    private AreaEntity location;

    @ManyToOne(
            targetEntity = CategoryEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "category")
    private CategoryEntity categoryEntity;

    public ShopEntity() {
    }

    public ShopEntity(Long id, String name, UserEntity owner, AreaEntity location, CategoryEntity categoryEntity) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.location = location;
        this.categoryEntity = categoryEntity;
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

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public AreaEntity getLocation() {
        return location;
    }

    public void setLocation(AreaEntity location) {
        this.location = location;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
