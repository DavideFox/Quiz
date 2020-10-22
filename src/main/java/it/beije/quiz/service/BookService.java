package it.beije.quiz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.quiz.entity.Book;
import it.beije.quiz.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book load(Integer id) {
		Optional<Book> book = bookRepository.findById(id);

		return book.isPresent() ? book.get() : null;

	}

	public Integer lastId() {

		Optional<Book> book = bookRepository.findTopByOrderByIdDesc();

		return book.isPresent() ? book.get().getId() : 0;

	}

	public Integer getBookIdByTitle(String title) {

		Optional<Book> book = bookRepository.findByTitle(title);

		return book.isPresent() ? book.get().getId() : null;

	}

	public void insert(Book book) {

		if (book == null) {
			throw new IllegalArgumentException("Book bean is null");
		}

		if (!"".equals(book.getTitle())) {

			bookRepository.save(book);

		} else {
			throw new IllegalArgumentException("Book bean data is not valid");
		}
	}
}
