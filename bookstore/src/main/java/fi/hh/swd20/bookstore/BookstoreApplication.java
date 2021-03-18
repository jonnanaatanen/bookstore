package fi.hh.swd20.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import fi.hh.swd20.bookstore.domain.Book;
import fi.hh.swd20.bookstore.domain.BookRepository;
import fi.hh.swd20.bookstore.domain.Category;
import fi.hh.swd20.bookstore.domain.CategoryRepository;
import fi.hh.swd20.bookstore.domain.User;
import fi.hh.swd20.bookstore.domain.UserRepository;

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
	public CommandLineRunner demo(CategoryRepository cRepository,BookRepository repository, UserRepository urepository) {
	return (args) -> {
		
	 Category category1 = new Category("Scifi");
	 Category category2 = new Category("Comic");
	 Category category3 = new Category("Fantasy");
	 
	 cRepository.save(category1);
	 cRepository.save(category2);
	 cRepository.save(category3);
		
	 Book b1 = new Book("Harry Potter", "J.K Rowling", 2005, "1234-87", 50, category1);
	 Book b2 = new Book("Keittokirja", "Joku Jokunen", 2020, "1267-90", 35, category2);
	 Book b3 = new Book("Tietokirja", "Tero Tieteilijä", 2005, "5784-65", 20, category3);
	
	repository.save(b1);
	repository.save(b2);
	repository.save(b3);
	
	// Create users: admin/admin user/user
	 User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@user.com", "USER");
	 User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@admin.com", "ADMIN");
	 urepository.save(user1);
	 urepository.save(user2);
	
	log.info("fetch all books");
	for (Book book : repository.findAll()) {
		log.info(book.toString());
	}
	log.info("fetch all categories");
	for (Category category : cRepository.findAll()) {
		log.info(category.toString());
	}
	};
}
}
