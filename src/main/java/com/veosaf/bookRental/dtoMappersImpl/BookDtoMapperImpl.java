package com.veosaf.bookRental.dtoMappersImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.veosaf.bookRental.dto.BookDto;
import com.veosaf.bookRental.dtoMappers.BookDtoMapper;
import com.veosaf.bookRental.models.Book;

@Service
public class BookDtoMapperImpl implements BookDtoMapper {

	@Override
	public BookDto toBookDto(Book book) {
		BookDto bookDto = null;
		if (book != null) {
			bookDto = new BookDto();
			bookDto.setId(book.getId());
			bookDto.setTitle(book.getTitle());
			bookDto.setAuthor(book.getAuthor());
			bookDto.setPublicationDate(book.getPublicationDate());
		}
		return bookDto;
	}

	@Override
	public List<BookDto> toBooksDto(List<Book> books) {
		List<BookDto> booksDto = new ArrayList<BookDto>();
		if (books != null) {
			books.stream().forEach(book -> booksDto.add(toBookDto(book)));
		}
		return booksDto;
	}

}
