package com.veosaf.bookRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veosaf.bookRental.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByTitle(String title);

	Book findById(Long id);

}
