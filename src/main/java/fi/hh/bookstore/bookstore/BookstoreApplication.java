package fi.hh.bookstore.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.bookstore.bookstore.domain.Book;
import fi.hh.bookstore.bookstore.domain.BookRepository;
import fi.hh.bookstore.bookstore.domain.Category;
import fi.hh.bookstore.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);

	}

@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("History"));
			crepository.save(new Category("Educational"));
			crepository.save(new Category("Drama"));
			
			
			//brepository.save(new Book("Maths", "yazeed", "1234", 1990, 24.0,crepository.findByName("Educational")));
			//brepository.save(new Book("English", "Mati", "12345", 1991, 30.0));

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}