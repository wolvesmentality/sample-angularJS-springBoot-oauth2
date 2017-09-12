package com.veosaf.bookRental.dtoMappers;

import com.veosaf.bookRental.dto.UserDto;
import com.veosaf.bookRental.models.User;

public interface UserDtoMapper {

	UserDto toUsrDto(User user);

}
