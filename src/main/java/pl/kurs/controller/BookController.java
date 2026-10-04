package pl.kurs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.error.BookDoesNotExistException;
import pl.kurs.model.Book;
import pl.kurs.request.*;
import pl.kurs.response.BookResponse;
import pl.kurs.response.BorrowBookResponse;
import pl.kurs.response.IsAvailableResponse;
import pl.kurs.service.BookService;

import java.util.Optional;

@RequestMapping("/api/books")
@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBookOrder(@RequestBody CreateBookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Integer id) {
        Book book = bookService.getBookById(id);
        BookResponse response = new BookResponse(id, book.getTitle(), book.getAuthor(), book.getDeposit(), book.isAvalible());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/details")
    public ResponseEntity<BookResponse> updateBookDetails(@RequestBody UpdateBookDetailsRequest request, @PathVariable Integer id) {
        BookResponse response = bookService.updateBookDetails(request, id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/deposit")
    public ResponseEntity<BookResponse> updateBookDepositPrice(@RequestBody UpdateBookDepositPrice request, @PathVariable Integer id) {
        BookResponse response = bookService.updateBookDepositPrice(request, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        if (bookService.deleteBookById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/{id}/borrow")
    public ResponseEntity<BorrowBookResponse> borrowBook(@PathVariable Integer id, @RequestBody BorrowBookRequest request) {
        BorrowBookResponse response = bookService.borrowBook(request, id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/available")
    public ResponseEntity<IsAvailableResponse> isAvailable(@RequestBody IsAvailableRequest request) {
        IsAvailableResponse response = bookService.checkBookAvailability(request);
        return ResponseEntity.ok(response);
    }




}
