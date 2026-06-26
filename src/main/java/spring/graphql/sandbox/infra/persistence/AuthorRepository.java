package spring.graphql.sandbox.infra.persistence;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.data.repository.CrudRepository;

import spring.graphql.sandbox.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Window<Author> findByOrderByIdAsc(ScrollPosition position, Limit limit);
}
