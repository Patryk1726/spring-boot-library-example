package pl.kurs.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandling {
    @ExceptionHandler(BookDoesNotExistException.class)
    public ResponseEntity<ErrorDto> bookDoesNotExistException(BookDoesNotExistException exc) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("Order with id: " + exc.getId() + " does not exist"));
    }
    @ExceptionHandler(BookIsNotAvailableException.class)
    public ResponseEntity<ErrorDto> bookIsNotAvailableException(BookIsNotAvailableException exc) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto("Order widt id: " + exc.getId() + " is not available"));
    }

    public record ErrorDto(String message) {
    }

    ;
}
