package pl.kurs.response;

import java.math.BigDecimal;

public record BorrowBookResponse(Integer id, String author, String title, BigDecimal price) {
}
