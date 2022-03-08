package dev.sma.basic.service;

import dev.sma.basic.dao.UserDao;
import dev.sma.basic.dto.UserDto;
import dev.sma.basic.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserDao userDao;

    public UserService(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }

    public void joinUser(UserDto userDto) {
        this.userDao.joinUser(userDto);
    }

    public UserDto readUser(int id) {
        UserEntity userEntity = this.userDao.readUser(id);
        return new UserDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }

    public List<UserDto> readAllUser() {
        Iterator<UserEntity> iterator = this.userDao.readAllUser();
        List<UserDto> userDtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();
            userDtoList.add(new UserDto(
                    userEntity.getId(),
                    userEntity.getName(),
                    userEntity.getEmail()
                    ));
        }
        return userDtoList;
    }

    public void updateUser(int id, UserDto userDto) {
        this.userDao.updateUser(id, userDto);
    }

    public void deleteUser(int id) {
        this.userDao.deleteUser(id);
    }

}
