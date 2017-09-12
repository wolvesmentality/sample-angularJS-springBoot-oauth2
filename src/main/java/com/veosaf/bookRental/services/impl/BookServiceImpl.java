package com.veosaf.bookRental.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.veosaf.bookRental.dto.BookDto;
import com.veosaf.bookRental.dtoMappers.BookDtoMapper;
import com.veosaf.bookRental.models.Book;
import com.veosaf.bookRental.repository.BookRepository;
import com.veosaf.bookRental.services.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Inject
	private BookRepository bookRepository;

	@Inject
	private BookDtoMapper bookDtoMapper;

	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	@Transactional
	public List<BookDto> findAllBooksDto() {
		return bookDtoMapper.toBooksDto(bookRepository.findAll());
	}

	@Override
	public Book findByTitle(String title) {
		return bookRepository.findByTitle(title);
	}

	@Override
	public BookDto findById(Long id) {
		return bookDtoMapper.toBookDto(bookRepository.findById(id));
	}

}
