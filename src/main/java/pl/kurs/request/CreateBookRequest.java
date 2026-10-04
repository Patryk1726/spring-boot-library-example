package pl.kurs.request;

import java.math.BigDecimal;

public record CreateBookRequest(String title, String author, BigDecimal price) {
}
