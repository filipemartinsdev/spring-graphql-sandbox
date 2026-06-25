package spring.graphql.sandbox.infra.web;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import spring.graphql.sandbox.domain.Author;
import spring.graphql.sandbox.infra.persistence.AuthorRepository;

// Instead of RestController on springbootMVC
@Controller
public class AuthorController {
  private final AuthorRepository authorRepository;

  public AuthorController(AuthorRepository authorRepository){
    this.authorRepository = authorRepository;
  }

  @QueryMapping()
  public Iterable<Author> authors(){
    return authorRepository.findAll();
  }
}
