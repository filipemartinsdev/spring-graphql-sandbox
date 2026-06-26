package spring.graphql.sandbox.infra.web;

import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import spring.graphql.sandbox.application.service.BookService;
import spring.graphql.sandbox.application.service.BookService.BookInput;
import spring.graphql.sandbox.domain.Book;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public Iterable<Book> books(){
        return bookService.getAll();
    }

    @QueryMapping
    public Optional<Book> bookById(@Argument Long id){
        return bookService.getById(id);
    }
    
    @MutationMapping
    public Book createBook(@Argument(name = "book") BookInput bookInput){
        return bookService.create(bookInput);
    }
}
