package pl.kurs.error;

import lombok.Value;

@Value
public class BookIsNotAvailableException extends RuntimeException {
    Integer id;
}
