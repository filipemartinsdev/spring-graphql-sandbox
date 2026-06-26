package spring.graphql.sandbox.infra.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.query.ScrollSubrange;
import org.springframework.stereotype.Controller;

import io.github.responsekit.core.PagedResponse;
import spring.graphql.sandbox.domain.Author;
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

  @QueryMapping
  public Window<Author> pagedAuthors(ScrollSubrange subrange){
    ScrollPosition position = subrange.position().orElse(ScrollPosition.keyset());

    Limit limit = Limit.of(subrange.count().orElse(10));

    return authorRepository.findByOrderByIdAsc(position, limit);
  }
}
