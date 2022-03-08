package dev.sma.basic.dao;

import dev.sma.basic.dto.UserDto;
import dev.sma.basic.entity.user.UserEntity;
import dev.sma.basic.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.Optional;

@Repository
public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    private final UserRepository userRepository;

    public UserDao(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void joinUser(UserDto userDto) {
        Optional<UserEntity> targetEntity = this.userRepository.findByEmail(userDto.getEmail());
        if (targetEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPostEntityList(null);
        this.userRepository.save(userEntity);
    }

    public UserEntity readUser(int id) {
        Optional<UserEntity> targetEntity = this.userRepository.findById((long) id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return targetEntity.get();
    }

    public Iterator<UserEntity> readAllUser() {
        return this.userRepository.findAll().iterator();
    }

    public void updateUser(int id, UserDto userDto) {
        Optional<UserEntity> targetEntity = this.userRepository.findById((long) id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserEntity userEntity = targetEntity.get();
        userEntity.setName(userDto.getName() == null ? userEntity.getName() : userDto.getName());
        userEntity.setEmail(userDto.getEmail() == null ? userEntity.getEmail() : userDto.getEmail());
        this.userRepository.save(userEntity);
    }

    public void deleteUser(int id) {
        Optional<UserEntity> targetEntity = this.userRepository.findById((long) id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.userRepository.delete(targetEntity.get());
    }

}
