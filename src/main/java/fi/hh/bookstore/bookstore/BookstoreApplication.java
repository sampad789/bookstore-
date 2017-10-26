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
import fi.hh.bookstore.bookstore.domain.User;
import fi.hh.bookstore.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);

	}

@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository,UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of categories");
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("History"));
			crepository.save(new Category("Educational"));
			crepository.save(new Category("Drama"));
			
			log.info("save a couple of books");
			brepository.save(new Book("Maths", "yazeed", "1234", 1990, 24.0,crepository.findByName("Educational").get(0)));
			brepository.save(new Book("English", "Mati", "12345", 1991, 30.0,crepository.findByName("Educational").get(0)));

			// Create users: admin/admin user/user
						User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			 			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
						urepository.save(user1);
						urepository.save(user2);
			
			
			
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}