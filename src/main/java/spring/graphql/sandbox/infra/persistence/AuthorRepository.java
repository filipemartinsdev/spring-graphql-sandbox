package spring.graphql.sandbox.infra.persistence;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.graphql.sandbox.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Window<Author> findByOrderByIdAsc(ScrollPosition position, Limit limit);
}
