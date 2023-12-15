package com.belhard.bookstore.service.user;

import com.belhard.bookstore.dao.user.UserDao;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> findAll() {
        try {
            List<User> users = userDao.getAll();
            List<UserDto> userDtos = new ArrayList<>();

            for (User user : users) {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setFirstName(user.getFirstName());
                userDto.setLastName(user.getLastName());
                userDto.setEmail(user.getEmail());
                userDto.setDateOfBirth(user.getDateOfBirth());
                userDto.setGender(user.getGender());
                userDto.setPhoneNumber(user.getPhoneNumber());
                userDto.setPassword(user.getPassword());

                userDtos.add(userDto);
            }

            return userDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto create(UserDto dto) {
        try {
            User userEntity = new User();
            userEntity.setFirstName(dto.getFirstName());
            userEntity.setLastName(dto.getLastName());
            userEntity.setEmail(dto.getEmail());
            userEntity.setDateOfBirth(dto.getDateOfBirth());
            userEntity.setGender(dto.getGender());
            userEntity.setPhoneNumber(dto.getPhoneNumber());
            userEntity.setPassword(dto.getPassword());

            userDao.create(userEntity);

            return dto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto update(UserDto dto) {
        try {
            User userEntity = new User();
            userEntity.setId(dto.getId());
            userEntity.setFirstName(dto.getFirstName());
            userEntity.setLastName(dto.getLastName());
            userEntity.setEmail(dto.getEmail());
            userEntity.setDateOfBirth(dto.getDateOfBirth());
            userEntity.setGender(dto.getGender());
            userEntity.setPhoneNumber(dto.getPhoneNumber());
            userEntity.setPassword(dto.getPassword());

            userDao.update(userEntity);

            return dto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            userDao.delete(Math.toIntExact(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDto findByEmail(String email) {
        try {
            User userEntity = userDao.findByEmail(email);

            if (userEntity == null) {
                throw new IllegalArgumentException("User with email" + email + "not found");
            }

            UserDto dto = new UserDto();
            dto.setId(userEntity.getId());
            dto.setFirstName(userEntity.getFirstName());
            dto.setLastName(userEntity.getLastName());
            dto.setEmail(userEntity.getEmail());
            dto.setDateOfBirth(userEntity.getDateOfBirth());
            dto.setGender(userEntity.getGender());
            dto.setPhoneNumber(userEntity.getPhoneNumber());
            dto.setPassword(userEntity.getPassword());

            return dto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserDto> findByLastName(String lastName) {
        try {
            List<User> userEntities = userDao.findByLastName(lastName);

            List<UserDto> dtos = new ArrayList<>();

            for (User userEntity : userEntities) {
                UserDto dto = new UserDto();
                dto.setId(userEntity.getId());
                dto.setFirstName(userEntity.getFirstName());
                dto.setLastName(userEntity.getLastName());
                dto.setEmail(userEntity.getEmail());
                dto.setDateOfBirth(userEntity.getDateOfBirth());
                dto.setGender(userEntity.getGender());
                dto.setPhoneNumber(userEntity.getPhoneNumber());
                dto.setPassword(userEntity.getPassword());

                dtos.add(dto);
            }

            return dtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
