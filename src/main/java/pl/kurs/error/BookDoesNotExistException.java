package pl.kurs.error;

import lombok.Value;
import org.springframework.validation.annotation.Validated;

@Value
public class BookDoesNotExistException extends RuntimeException {
    Integer id;
}
