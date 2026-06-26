package spring.graphql.sandbox.infra.web;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.query.ScrollSubrange;
import org.springframework.stereotype.Controller;

import spring.graphql.sandbox.application.service.AuthorService;
import spring.graphql.sandbox.domain.Author;

// Instead of RestController on Spring Boot MVC
@Controller
public class AuthorController {
  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
}

  @QueryMapping
  public List<Author> authors(){
    return authorService.getAll();
  }

  @QueryMapping
  public Optional<Author> authorById(@Argument Long id){
    return authorService.getById(id);
  }

  @QueryMapping
  public Window<Author> pagedAuthors(ScrollSubrange subrange){
    ScrollPosition position = subrange.position().orElse(ScrollPosition.keyset());

    Limit limit = Limit.of(subrange.count().orElse(20));

    return authorService.getAll(position, limit);
  }

  @MutationMapping
  public Author createAuthor(@Argument AuthorInput author){
    return authorService.create(author);
  }

  public static record AuthorInput (
    String name,
    String bio
  ){}
}
