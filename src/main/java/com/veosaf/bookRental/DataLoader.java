package com.veosaf.bookRental;

import java.util.GregorianCalendar;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.veosaf.bookRental.models.Book;
import com.veosaf.bookRental.models.User;
import com.veosaf.bookRental.models.UserCredential;
import com.veosaf.bookRental.repository.BookRepository;
import com.veosaf.bookRental.repository.UserCredentialRepository;
import com.veosaf.bookRental.repository.UserRepository;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

	@Inject
	private BookRepository bookRepository;

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserCredentialRepository userCredentialRepository;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		addBooks();
		addUsers();
	}

	private void addBooks() {
		Book book = new Book();
		book.setTitle("Java Book");
		book.setPublicationDate(new GregorianCalendar(2017, 0, 31).getTime());
		book.setAuthor("Simon");
		bookRepository.save(book);

		book = new Book();
		book.setTitle("Php Book");
		book.setPublicationDate(new GregorianCalendar(2016, 0, 31).getTime());
		book.setAuthor("Chris");
		bookRepository.save(book);
	}

	private void addUsers() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Jackson");
		user.setEmail("john@gmail.com");
		user = userRepository.save(user);

		UserCredential userCredential = new UserCredential();
		user.setUserCredential(userCredential);
		userCredential.setUser(user);
		userCredential.setUsername("john");
		userCredential.setPassword("abc");
		userCredential = userCredentialRepository.save(userCredential);

	}

}
