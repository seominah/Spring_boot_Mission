package dev.sma.basic.model;

import dev.sma.basic.jpa.entity.AreaEntity;
import dev.sma.basic.jpa.entity.UserEntity;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private AreaEntity residence;
    private boolean isShopOwner;

    public UserDto() {
    }

    public UserDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.residence = userEntity.getResidence();
        this.isShopOwner = userEntity.isShopOwner();
    }

    public UserDto(Long id, String username, String password, AreaEntity residence, boolean isShopOwner) {
        this.id = id;
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", residence=" + residence +
                ", isShopOwner=" + isShopOwner +
                '}';
    }
}
