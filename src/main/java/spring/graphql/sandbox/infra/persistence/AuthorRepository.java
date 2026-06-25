package spring.graphql.sandbox.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.graphql.sandbox.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
  
}
