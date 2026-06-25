package spring.graphql.sandbox.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.graphql.sandbox.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
  
}
