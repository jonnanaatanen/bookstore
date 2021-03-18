package fi.hh.swd20.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.swd20.bookstore.domain.Book;
import fi.hh.swd20.bookstore.domain.BookRepository;
import fi.hh.swd20.bookstore.domain.CategoryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository categoryrepository;
	
	@RequestMapping(value="/login")
		public String login() {
			return "login";
		}    
	
	
	@RequestMapping(value= "/booklist", method = RequestMethod.GET)
	public String getBooks(Model model) {
		List<Book> books = (List<Book>) repository.findAll(); // haetaan kirjat tietokannasta
		model.addAttribute("books", books); // välitetään kirjalista html-templatelle model olion avulla
		return "booklist"; // booklist.html
	}
	
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    

	// RESTful service to get book by id
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    } 
	
    // RESTful service to save new book
    @RequestMapping(value="/books", method = RequestMethod.POST)
    public @ResponseBody Book saveBookRest(@RequestBody Book book) {	
    	return repository.save(book);
    }
    
	 //palauttaa kirjan lisäyslomakkeen
    @RequestMapping(value = "/addbook")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", categoryrepository.findAll());
        return "addbook";
    }     
    //vastaanottaa kirjalomakkeen tiedot ja tallettaa ne tietokantaan
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    } 
    //poistaa kirjan id-arvon perusteella tietokannasta
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    //suojaa delete-toiminto metoditasolla @PreAuthorize-annotaatiolla vain ADMIN-roolin omaaville käyttäjille
   @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable(value="id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
    } 
    //kirjan tietojen muokkaus
    @RequestMapping(value = "/edit/{id}", method= RequestMethod.GET)
    public String editBook(@PathVariable(value="id") Long bookId, Model model){
    	model.addAttribute("categories", categoryrepository.findAll());
    	model.addAttribute("book", repository.findById(bookId));
    	return "editbook";
    }
    
}