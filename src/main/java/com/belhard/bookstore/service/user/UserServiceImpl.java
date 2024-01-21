package com.belhard.bookstore.service.user;

import com.belhard.bookstore.dao.user.UserDao;
import com.belhard.bookstore.dto.user.CreateUserDto;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.entity.User;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> findAll() {
        try {
            log.debug("Fetching all users");
            List<User> users = userDao.getAll();
            List<UserDto> userDtos = new ArrayList<>();

            for (User user : users) {
                UserDto userDto = userReadDto(user);

                userDtos.add(userDto);
            }

            log.debug("All users received");

            return userDtos;
        } catch (SQLException e) {
            log.error("Failed to find users", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto create(CreateUserDto dto) {
        try {
            log.debug("Creating user: {}", dto);
            User user = toEntity(dto);
            User created = userDao.create(user);

            log.debug("User created: {}", user);

            return userReadDto(created);
        } catch (SQLException e) {
            log.error("Failed to create user: {}", dto, e);
            throw new RuntimeException(e);
        }
    }

    private static User toEntity(CreateUserDto dto) {
        User userEntity = new User();
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setDateOfBirth(dto.getDateOfBirth());
        userEntity.setGender(dto.getGender());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userEntity.setPassword(dto.getPassword());
        return userEntity;
    }

    @Override
    public UserDto update(UserDto dto) {
        try {
            log.debug("Updating user: {}", dto);
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

            log.debug("User updated: {}", userEntity);

            return dto;
        } catch (SQLException e) {
            log.error("Failed to update user: {}", dto, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            log.debug("Deleting user: {}", id);

            userDao.delete(id);

            log.debug("User deleted: {}", id);
        } catch (SQLException e) {
            log.error("Failed to delete user: {}", id, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto findByEmail(String email) {
        try {
            log.debug("Fetching user by email: {}", email);
            User userEntity = userDao.findByEmail(email);

            if (userEntity == null) {
                throw new IllegalArgumentException("User with email " + email + " not found");
            }

            UserDto dto = userReadDto(userEntity);

            log.debug("User received: {}", dto);

            return dto;
        } catch (SQLException e) {
            log.error("Failed to find user: {}", email, e);
            throw new RuntimeException(e);
        }
    }

    private static UserDto userReadDto(User userEntity) {
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
    }

    @Override
    public List<UserDto> findByLastName(String lastName) {
        try {
            log.debug("Fetching user by lastName: {}", lastName);
            List<User> userEntities = userDao.findByLastName(lastName);

            List<UserDto> dtos = new ArrayList<>();

            for (User userEntity : userEntities) {
                UserDto dto = userReadDto(userEntity);

                dtos.add(dto);
            }

            log.debug("User received");

            return dtos;
        } catch (SQLException e) {
            log.error("Failed to find user: {}", lastName, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto findById(Long id) {
        try {
            log.debug("Fetching user by ID: {}", id);

            User userEntity = userDao.read(id);
            UserDto dto = userReadDto(userEntity);

            log.debug("User received", dto);
            return dto;
        } catch (SQLException e) {
            log.error("Failed to find user: {}", id, e);
            throw new RuntimeException(e);
        }
    }
}
