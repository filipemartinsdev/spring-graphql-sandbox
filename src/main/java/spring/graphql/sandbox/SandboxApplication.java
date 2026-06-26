package spring.graphql.sandbox;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

import spring.graphql.sandbox.domain.Author;
import spring.graphql.sandbox.domain.Book;
import spring.graphql.sandbox.infra.persistence.AuthorRepository;
import spring.graphql.sandbox.infra.persistence.BookRepository;

@SpringBootApplication
public class SandboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SandboxApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(){
		return args -> {
			testQuery();
		};
	}

	private void testQuery(){
		WebClient webClient = WebClient.builder()
			.baseUrl("http://localhost:8080/graphql")
			.build();

		HttpGraphQlClient client = HttpGraphQlClient.builder(webClient)
			.build();
		
		List<Book> books = client.documentName("books")
			.retrieve("books")
			.toEntityList(Book.class)
			.block();

		System.out.println("Books: " + books);
	}
}
