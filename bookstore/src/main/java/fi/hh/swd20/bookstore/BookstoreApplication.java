package fi.hh.swd20.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.swd20.bookstore.domain.Book;
import fi.hh.swd20.bookstore.domain.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log =
			LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
	return (args) -> {
	 Book b1 = new Book("Harry Potter", "J.K Rowling", 2005, "1234-87", 50);
	 Book b2 = new Book("Keittokirja", "Joku Jokunen", 2020, "1267-90", 35);
	 Book b3 = new Book("Tietokirja", "Tero Tieteilijä", 2005, "5784-65", 20);
	
	repository.save(b1);
	repository.save(b2);
	repository.save(b3);
	
	log.info("fetch all books");
	for (Book book : repository.findAll()) {
		log.info(book.toString());
	}
	};
}
}
