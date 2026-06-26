package spring.graphql.sandbox.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.stereotype.Service;

import spring.graphql.sandbox.domain.Author;
import spring.graphql.sandbox.infra.persistence.AuthorRepository;
import spring.graphql.sandbox.infra.web.AuthorController.AuthorInput;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll(){
        return authorRepository.findAll();
    }

    public Window<Author> getAll(ScrollPosition position, Limit limit){
        return authorRepository.findByOrderByIdAsc(position, limit);
    }

    public Optional<Author> getById(Long id) {
        return authorRepository.findById(id);
    }

    public Author create(AuthorInput author) {
        Author newAuthor = new Author();
        newAuthor.setName(author.name());
        newAuthor.setBio(author.bio());
        return authorRepository.save(newAuthor);
    }
}
