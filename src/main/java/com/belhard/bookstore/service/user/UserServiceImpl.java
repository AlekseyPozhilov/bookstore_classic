package com.belhard.bookstore.service.user;

import com.belhard.bookstore.dao.user.UserDao;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> findAll() {
        try {
            logger.debug("Fetching all users");
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

            logger.debug("All users received");

            return userDtos;
        } catch (SQLException e) {
            logger.error("Failed to find users", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto create(UserDto dto) {
        try {
            logger.debug("Creating user: {}", dto);
            User userEntity = new User();
            userEntity.setFirstName(dto.getFirstName());
            userEntity.setLastName(dto.getLastName());
            userEntity.setEmail(dto.getEmail());
            userEntity.setDateOfBirth(dto.getDateOfBirth());
            userEntity.setGender(dto.getGender());
            userEntity.setPhoneNumber(dto.getPhoneNumber());
            userEntity.setPassword(dto.getPassword());

            userDao.create(userEntity);

            logger.debug("User created: {}", userEntity);

            return dto;
        } catch (SQLException e) {
            logger.error("Failed to create user: {}", dto, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto update(UserDto dto) {
        try {
            logger.debug("Updating user: {}", dto);
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

            logger.debug("User updated: {}", userEntity);

            return dto;
        } catch (SQLException e) {
            logger.error("Failed to update user: {}", dto, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            logger.debug("Deleting user: {}", id);

            userDao.delete(Math.toIntExact(id));

            logger.debug("User deleted: {}", id);
        } catch (SQLException e) {
            logger.error("Failed to delete user: {}", id, e);
            throw new RuntimeException(e);
        }
    }

    public UserDto findByEmail(String email) {
        try {
            logger.debug("Fetching user by email: {}", email);
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

            logger.debug("User received: {}", dto);

            return dto;
        } catch (SQLException e) {
            logger.error("Failed to find user: {}", email, e);
            throw new RuntimeException(e);
        }
    }

    public List<UserDto> findByLastName(String lastName) {
        try {
            logger.debug("Fetching user by lastName: {}", lastName);
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

            logger.debug("User received");

            return dtos;
        } catch (SQLException e) {
            logger.error("Failed to find user: {}", lastName, e);
            throw new RuntimeException(e);
        }
    }
}
