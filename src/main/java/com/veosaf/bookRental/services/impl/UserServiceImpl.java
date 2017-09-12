package com.veosaf.bookRental.services.impl;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.veosaf.bookRental.dto.UserDto;
import com.veosaf.bookRental.dtoMappers.UserDtoMapper;
import com.veosaf.bookRental.models.User;
import com.veosaf.bookRental.repository.UserRepository;
import com.veosaf.bookRental.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserDtoMapper userDtoMapper;

	@Override
	public UserDto getByCredentials(String userName, String password) {
		return userDtoMapper.toUsrDto(userRepository.findByCredentials(userName, password));
	}

	@Override
	public UserDto getByEmail(String email) {
		return userDtoMapper.toUsrDto(userRepository.findByEmail(email));
	}

	@Override
	@Transactional
	public UserDto save(User user) {
		user = userRepository.save(user);
		return userDtoMapper.toUsrDto(user);
	}

}
