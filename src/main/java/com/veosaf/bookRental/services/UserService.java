package com.veosaf.bookRental.services;

import com.veosaf.bookRental.dto.UserDto;
import com.veosaf.bookRental.models.User;

public interface UserService {

	UserDto getByCredentials(String username, String password);

	UserDto getByEmail(String email);

	UserDto save(User user);
}
