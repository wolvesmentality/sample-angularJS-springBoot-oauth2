package com.veosaf.bookRental.resources.impl;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.veosaf.bookRental.dto.BookDto;
import com.veosaf.bookRental.resources.BookResource;
import com.veosaf.bookRental.services.BookService;

public class BookResourceImpl implements BookResource {

	@Inject
	private BookService bookBusinessService;

	@Override
	public Response findAll() {
		List<BookDto> booksDto = bookBusinessService.findAllBooksDto();
		return Response.ok(booksDto).build();
	}

	@Override
	public Response findById(Long id) {
		BookDto book = bookBusinessService.findById(id);
		return Response.ok(book).build();
	}

}
