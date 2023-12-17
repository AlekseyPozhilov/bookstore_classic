package com.belhard.bookstore.service.user;

import com.belhard.bookstore.dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto create(UserDto dto);

    UserDto update(UserDto dto);

    void delete(Long id);

    UserDto findByEmail(String email);

    List<UserDto> findByLastName(String lastName);
}
