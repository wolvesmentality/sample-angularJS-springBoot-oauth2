package com.veosaf.bookRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.veosaf.bookRental.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "Select uC.user from UserCredential uC where uC.username = ?1 and uC.password = ?2")
	User findByCredentials(String userName, String password);

	User findByEmail(String email);

}
