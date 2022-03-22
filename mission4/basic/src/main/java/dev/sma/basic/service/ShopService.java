package dev.sma.basic.service;

import dev.sma.basic.jpa.entity.AreaEntity;
import dev.sma.basic.jpa.entity.CategoryEntity;
import dev.sma.basic.jpa.entity.ShopEntity;
import dev.sma.basic.jpa.entity.UserEntity;
import dev.sma.basic.jpa.repository.AreaRepository;
import dev.sma.basic.jpa.repository.CategoryRepository;
import dev.sma.basic.jpa.repository.ShopRepository;
import dev.sma.basic.jpa.repository.UserRepository;
import dev.sma.basic.model.ShopDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    private static final Logger logger = LoggerFactory.getLogger(ShopService.class);
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AreaRepository areaRepository;

    public ShopService(@Autowired ShopRepository shopRepository,
                       @Autowired UserRepository userRepository,
                       @Autowired CategoryRepository categoryRepository,
                       @Autowired AreaRepository areaRepository) {
        this.shopRepository = shopRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.areaRepository = areaRepository;
    }

    public ShopDto createShop(ShopDto dto) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(dto.getOwnerId());
        if (userEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserEntity userEntity = userEntityOptional.get();
        if (!userEntity.isShopOwner()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Optional<CategoryEntity> categoryEntityOptional = this.categoryRepository.findById(dto.getCategory());
        if (categoryEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        CategoryEntity categoryEntity = categoryEntityOptional.get();

        Optional<AreaEntity> areaEntityOptional = this.areaRepository.findById(dto.getLocation());
        if (areaEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        AreaEntity areaEntity = areaEntityOptional.get();

        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setName(dto.getName());
        shopEntity.setOwner(userEntity);
        shopEntity.setCategoryEntity(categoryEntity);
        shopEntity.setLocation(areaEntity);
        shopEntity = this.shopRepository.save(shopEntity);

        return new ShopDto(shopEntity);
    }

    public ShopDto readShop(Long id) {
        Optional<ShopEntity> shopEntityOptional = this.shopRepository.findById(id);
        if (shopEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ShopDto(shopEntityOptional.get());
    }

    public Collection<ShopDto> readAllShop() {
        List<ShopDto> shopDtoList = new ArrayList<>();
        this.shopRepository.findAll().forEach(shopEntity -> shopDtoList.add(
                new ShopDto(shopEntity)
        ));
        return shopDtoList;
    }

    public void updateShop(Long id, ShopDto dto) {
        Optional<ShopEntity> shopEntityOptional = this.shopRepository.findById(id);
        if (shopEntityOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ShopEntity shopEntity = shopEntityOptional.get();
        if (!dto.getOwnerId().equals(shopEntity.getOwner().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        shopEntity.setName(dto.getName() == null ? shopEntity.getName() : dto.getName());
        this.shopRepository.save(shopEntity);
    }

    public void deleteShop(Long id) {
        if (!this.shopRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.shopRepository.deleteById(id);
    }


}
