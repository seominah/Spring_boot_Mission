package dev.sma.basic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sma.basic.jpa.entity.AreaEntity;
import dev.sma.basic.jpa.repository.AreaRepository;
import dev.sma.basic.model.AreaDto;
import dev.sma.basic.model.UserDto;
import dev.sma.basic.jpa.entity.UserEntity;
import dev.sma.basic.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class JpaUserService implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(JpaUserService.class);
    private final UserRepository userRepository;
    private final AreaRepository areaRepository;
    private final PasswordEncoder passwordEncoder;

    public JpaUserService(@Autowired UserRepository userRepository,
                          @Autowired AreaRepository areaRepository,
                          @Autowired PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.areaRepository = areaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private AreaEntity nResidence() {
        List<AreaEntity> areaEntityList = new ArrayList<>();
        this.areaRepository.findAll().forEach(areaEntity -> areaEntityList.add(areaEntity));
        Collections.shuffle(areaEntityList);
        return areaEntityList.get(0);
    }

    @Override
    public UserDto create(UserDto dto) {
//        Optional<AreaEntity> areaEntityOptional = this.areaRepository.findById(dto.getResidence().getId());
//        if (areaEntityOptional.isEmpty())
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        AreaEntity residence = areaEntityOptional.get();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userEntity.setResidence(nResidence());
        logger.info(userEntity.getResidence().toString());
        userEntity.setShopOwner(dto.isShopOwner());
        userEntity = this.userRepository.save(userEntity);
        return new UserDto(userEntity);
    }

    @Override
    public UserDto read(Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity userEntity = userEntityOptional.get();
        return new UserDto(userEntity);
    }

    @Override
    public Collection<UserDto> readAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        this.userRepository.findAll().forEach(userEntity -> userDtoList.add(
                new UserDto(userEntity)));
        return userDtoList;
    }

    @Override
    public boolean update(Long id, UserDto dto) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setPassword(
                dto.getPassword() == null ? userEntity.getPassword() : dto.getPassword()
        );
        this.userRepository.save(userEntity);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity userEntity = userEntityOptional.get();
        this.userRepository.delete(userEntity);
        return true;
    }
}
