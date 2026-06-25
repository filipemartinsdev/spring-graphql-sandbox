package spring.graphql.sandbox.infra.web;

import java.util.Optional;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import spring.graphql.sandbox.domain.Author;
import spring.graphql.sandbox.domain.Book;
import spring.graphql.sandbox.infra.persistence.AuthorRepository;

// Instead of RestController on Spring Boot MVC
@Controller
public class AuthorController {
  private final AuthorRepository authorRepository;

  public AuthorController(AuthorRepository authorRepository){
    this.authorRepository = authorRepository;
  }

  @QueryMapping
  public Iterable<Author> authors(){
    return authorRepository.findAll();
  }

  @QueryMapping
  public Optional<Author> authorById(@Argument Long id){
    return authorRepository.findById(id);
  }
}
