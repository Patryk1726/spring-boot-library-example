package pl.kurs.service;

import org.springframework.stereotype.Service;
import pl.kurs.error.BookDoesNotExistException;
import pl.kurs.error.BookIsNotAvailableException;
import pl.kurs.model.Book;
import pl.kurs.request.*;
import pl.kurs.response.BookResponse;
import pl.kurs.response.BorrowBookResponse;
import pl.kurs.response.IsAvailableResponse;
import pl.kurs.service.strategy.BookPlan;
import pl.kurs.service.strategy.BookStrategy;


import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BookService {
    private Map<BookPlan, BookStrategy> bookStrategies;
    private List<Book> books = new CopyOnWriteArrayList<>();
    private AtomicInteger counter = new AtomicInteger(1);

    public BookService(Set<BookStrategy> bookStrategies) {
        this.bookStrategies = bookStrategies.stream().collect(Collectors.toMap(BookStrategy::getBookPlan, bookStrategy -> bookStrategy));
    }

    public BookResponse createBook(CreateBookRequest req) {
        Book book = new Book(counter.getAndIncrement(), req.title(), req.author(), req.price(), true);
        books.add(book);
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getDeposit(), book.isAvalible());
    }

    public Book getBookById(Integer id) {
        return Optional.ofNullable(books)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookDoesNotExistException(id));
    }

    public BookResponse updateBookDetails(UpdateBookDetailsRequest req, Integer id) {
        Book book = getBookById(id);

        book.setTitle(req.title());
        book.setAuthor(req.author());

        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getDeposit(), book.isAvalible());
    }

    public BookResponse updateBookDepositPrice(UpdateBookDepositPrice req, Integer id) {
        Book book = getBookById(id);
        book.setDeposit(req.depositPrice());
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getDeposit(), book.isAvalible());
    }

    public boolean deleteBookById(Integer id) {
        return books.removeIf(book -> book.getId().equals(id));

    }

    public BorrowBookResponse borrowBook(BorrowBookRequest req, Integer id) {
        Book book = getBookById(id);
        if (!book.isAvalible()) {
            throw new BookIsNotAvailableException(id);
        }
        book.setAvalible(false);
        BookStrategy strategy = bookStrategies.get(req.bookPlan());
        BigDecimal calculatedPrice = strategy.getBookPrice(book.getDeposit());
        return new BorrowBookResponse(book.getId(), book.getAuthor(), book.getTitle(), calculatedPrice);
    }

    public IsAvailableResponse checkBookAvailability(IsAvailableRequest req) {
        Boolean isAvailable = books.stream()
                .anyMatch(book -> book.isAvalible()
                && book.getTitle().equals(req.title())
                && book.getAuthor().equals(req.author()));
        return new IsAvailableResponse(isAvailable);
    }

}
