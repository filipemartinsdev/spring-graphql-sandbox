package spring.graphql.sandbox;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import spring.graphql.sandbox.domain.Author;
import spring.graphql.sandbox.domain.Book;
import spring.graphql.sandbox.infra.persistence.AuthorRepository;
import spring.graphql.sandbox.infra.persistence.BookRepository;

@SpringBootApplication
public class SandboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SandboxApplication.class, args);
	}

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;

	public SandboxApplication(AuthorRepository authorRepository, BookRepository bookRepository){
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	@Bean
	public CommandLineRunner runner(){
		return args -> {
			if (bookRepository.count() == 0) {
				populateDB();
			}
		};
	}

	private void populateDB(){
		var douglasAdams = new Author();
		douglasAdams.setName("Douglas Adams");
		
		var book1 = new Book();
		book1.setAuthor(douglasAdams);
		book1.setTitle("O guia definitivo do mochileiro das galaxias");
		douglasAdams.getBooks().add(book1);

		authorRepository.save(douglasAdams);	

		var george = new Author();
		george.setName("George Orwell");
		
		var book2 = new Book();
		book2.setAuthor(george);
		book2.setTitle("1984");
		george.getBooks().add(book2);

		var book3 = new Book();
		book3.setTitle("A revolução dos bichos");
		book3.setAuthor(george);
		george.getBooks().add(book3);

		authorRepository.save(george);
	}
}
