package fi.hh.swd20.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import fi.hh.swd20.bookstore.domain.Book;
import fi.hh.swd20.bookstore.domain.BookRepository;
import fi.hh.swd20.bookstore.domain.CategoryRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository categoryrepository;
	
	@RequestMapping(value= "/booklist", method = RequestMethod.GET)
	public String getBooks(Model model) {
		List<Book> books = (List<Book>) repository.findAll(); // haetaan kirjat tietokannasta
		model.addAttribute("books", books); // välitetään kirjalista html-templatelle model olion avulla
		return "booklist"; // booklist.html
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
    public String deleteBook(@PathVariable(value="id") Long bookId) {
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