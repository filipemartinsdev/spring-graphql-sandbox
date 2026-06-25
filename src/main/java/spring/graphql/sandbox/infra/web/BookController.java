package spring.graphql.sandbox.infra.web;

import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import spring.graphql.sandbox.domain.Book;
import spring.graphql.sandbox.infra.persistence.AuthorRepository;
import spring.graphql.sandbox.infra.persistence.BookRepository;

@Controller
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public Iterable<Book> books(){
        return bookRepository.findAll();
    }

    @QueryMapping
    public Optional<Book> bookById(@Argument Long id){
        return bookRepository.findById(id);
    }
    
    @MutationMapping
    public Book createBook(@Argument(name = "book") BookInput bookInput){
        var book = new Book();
        
        book.setTitle(bookInput.title);
        book.setDescription(bookInput.description);
        
        book.setAuthor(
        authorRepository.findById(bookInput.authorId())
            .orElseThrow(() -> new RuntimeException("Author not found by ID: "+bookInput.authorId()))
        );

        return bookRepository.save(book);
    }

    static record BookInput (
        String title,
        String description,
        Long authorId
    ){
  }
}
