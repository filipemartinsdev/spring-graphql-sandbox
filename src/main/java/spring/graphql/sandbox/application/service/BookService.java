package spring.graphql.sandbox.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import spring.graphql.sandbox.domain.Book;
import spring.graphql.sandbox.infra.persistence.AuthorRepository;
import spring.graphql.sandbox.infra.persistence.BookRepository;

@Service
public class BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> getAll(){
        return this.bookRepository.findAll();   
    }

    public Optional<Book> getById(long id){
        return this.bookRepository.findById(id);
    }

    public Book create(BookInput bookInput) {

        var book = new Book();
        
        book.setTitle(bookInput.title);
        book.setDescription(bookInput.description);
        
        book.setAuthor(
        authorRepository.findById(bookInput.authorId())
            .orElseThrow(() -> new RuntimeException("Author not found by ID: "+bookInput.authorId()))
        );

        return bookRepository.save(book);
    }

    public static record BookInput (
        String title,
        String description,
        Long authorId
    ){}
}
