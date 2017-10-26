package fi.hh.bookstore.bookstore;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import fi.hh.bookstore.bookstore.domain.Book;
import fi.hh.bookstore.bookstore.domain.BookRepository;
import fi.hh.bookstore.bookstore.domain.CategoryRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
	public class BookRepositoryTests {
	
	@Autowired
	private BookRepository brepository;
	private CategoryRepository crepository;

	@Test
	public void findOneBookByAuthor() {
		List<Book> books = brepository.findByAuthor("Mati");
		assertThat(books).hasSize(1);
	}

	

	@Test
	public void createNewBook() {
		Book newbook = new Book("Claudio","The New Dawn", "123-2354", 2005,125.00, crepository.findByName("Fiction").get(0));
		brepository.save(newbook);
		assertThat(newbook.getId()).isNotNull();
	}
}
