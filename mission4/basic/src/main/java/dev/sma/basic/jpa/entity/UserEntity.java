package dev.sma.basic.jpa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "community_user")
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @OneToMany(
            targetEntity = PostEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "writer"
    )
    private List<PostEntity> writtenPosts;

    @ManyToOne(
            targetEntity = AreaEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "area_id")
    private AreaEntity residence;

    private boolean isShopOwner;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, List<PostEntity> writtenPosts, AreaEntity residence, boolean isShopOwner) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.writtenPosts = writtenPosts;
        this.residence = residence;
        this.isShopOwner = isShopOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PostEntity> getWrittenPosts() {
        return writtenPosts;
    }

    public void setWrittenPosts(List<PostEntity> writtenPosts) {
        this.writtenPosts = writtenPosts;
    }

    public AreaEntity getResidence() {
        return residence;
    }

    public void setResidence(AreaEntity residence) {
        this.residence = residence;
    }

    public boolean isShopOwner() {
        return isShopOwner;
    }

    public void setShopOwner(boolean shopOwner) {
        isShopOwner = shopOwner;
    }
}

