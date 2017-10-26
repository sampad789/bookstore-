package fi.hh.bookstore.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import fi.hh.bookstore.bookstore.domain.Book;
import fi.hh.bookstore.bookstore.domain.BookRepository;
import fi.hh.bookstore.bookstore.domain.CategoryRepository;
import fi.hh.bookstore.bookstore.domain.UserRepository;

@Controller

public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired 
	private UserRepository urepository;
	
	 @Autowired
	 private CategoryRepository crepository;
	
	 @RequestMapping(value="/login")
	    public String login() {	
	        return "login";
	    }	
	 @RequestMapping(value="/")
	    public String home() {	
	 		return "redirect:booklist";
	 }
	

	@RequestMapping(value = "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		model.addAttribute("users", urepository.findAll());
		return "booklist";
	}

	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.delete(bookId);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", crepository.findAll());
		return "addbook";
	
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findOne(bookId));
		model.addAttribute("categorys", crepository.findAll());
		return "editbook";
		}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value="books", method=RequestMethod.GET)
	     public @ResponseBody List<Book> bookListRest(){
	     		return (List<Book>) repository.findAll(); 
	     }
	     
	     @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	     public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {	
	     		return repository.findOne(bookId);
	    }
}
