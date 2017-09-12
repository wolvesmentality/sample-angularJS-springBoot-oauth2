package com.veosaf.bookRental.dtoMappersImpl;

import org.springframework.stereotype.Service;

import com.veosaf.bookRental.dto.UserDto;
import com.veosaf.bookRental.dtoMappers.UserDtoMapper;
import com.veosaf.bookRental.models.User;

@Service
public class UserDtoMapperImpl implements UserDtoMapper {

	@Override
	public UserDto toUsrDto(User user) {
		UserDto userDto = null;
		if (user == null) {
			return null;
		}
		userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());

		return userDto;
	}

}
