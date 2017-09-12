package com.veosaf.bookRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veosaf.bookRental.models.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {

}
