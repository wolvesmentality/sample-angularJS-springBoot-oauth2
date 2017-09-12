package com.veosaf.bookRental.services;

import java.util.List;

import com.veosaf.bookRental.dto.BookDto;
import com.veosaf.bookRental.models.Book;

public interface BookService {

	List<Book> findAllBooks();

	List<BookDto> findAllBooksDto();

	Book findByTitle(String title);

	BookDto findById(Long id);

}
