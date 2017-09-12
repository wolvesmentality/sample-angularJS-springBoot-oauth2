package com.veosaf.bookRental.dtoMappers;

import java.util.List;

import com.veosaf.bookRental.dto.BookDto;
import com.veosaf.bookRental.models.Book;

public interface BookDtoMapper {

	BookDto toBookDto(Book book);

	List<BookDto> toBooksDto(List<Book> books);

}
